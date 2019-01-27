package com.mc.block.img.file.web.dfs;

import com.mc.block.img.file.web.controller.FileContrller;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ConnectionPool {

    //最大连接数,可以写配置文件
    private final static int size = 5;
    //被使用的连接
    private ConcurrentHashMap<StorageClient1,Object> busyConnectionPool;
    //空闲的连接
    private ArrayBlockingQueue<StorageClient1> idleConnectionPool;
    
    private Object obj = new Object();
    
    private static ConnectionPool instance;
    
    public static ConnectionPool getConnectionPool() throws IOException, MyException {
        if(instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }
    
    //取出连接
    public StorageClient1 checkout(int waitTime){
        StorageClient1 storageClient1;
        try {
            storageClient1 = idleConnectionPool.poll(waitTime, TimeUnit.SECONDS);
            if(storageClient1 != null){
                busyConnectionPool.put(storageClient1, obj);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            storageClient1 = null;
            e.printStackTrace();
        }
        return storageClient1;
    }
    
    //回收连接
    public void checkin(StorageClient1 storageClient1){
        if(busyConnectionPool.remove(storageClient1) != null){
            idleConnectionPool.add(storageClient1);
        }
    }
    
    //如果连接无效则抛弃，新建连接来补充到池里
    public void drop(StorageClient1 storageClient1){
        if(busyConnectionPool.remove(storageClient1) != null){
            TrackerServer trackerServer = null;
            TrackerClient trackerClient = new TrackerClient();
            try {
                trackerServer = trackerClient.getConnection();
                StorageClient1 newStorageClient1 = new StorageClient1(trackerServer,null);
                idleConnectionPool.add(newStorageClient1);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                if(trackerServer != null){
                    try {
                        trackerServer.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    //单例
    private ConnectionPool() throws IOException, MyException {
        busyConnectionPool = new ConcurrentHashMap<>();
        idleConnectionPool = new ArrayBlockingQueue<>(size);
        init(size);
    }
    
    //初始化连接池
    private void init(int size) throws IOException, MyException {
        initClientGlobal();
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            trackerServer = trackerClient.getConnection();
            if (trackerServer == null) {
                throw new IllegalStateException("getConnection return null");
            }
            storageServer = trackerClient.getStoreStorage(trackerServer);
            if (storageServer == null) {
                throw new IllegalStateException("getStoreStorage return null");
            }
            for(int i=0; i<size; i++){
                StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
                idleConnectionPool.add(storageClient1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(trackerServer != null){
                try {
                    trackerServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(storageServer != null){
                try {
                    storageServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    //初始化客户端
    private void initClientGlobal() throws IOException, MyException {
        URL resource = FileContrller.class.getResource("/fdfs_client.conf");
        ClientGlobal.init(resource.getPath());
    }
    
    
}