package com.mc.block.img.file.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mc.block.commom.FileUtils;
import com.mc.block.commom.ResultUtil;
import com.mc.block.commom.StringUtils;
import com.mc.block.confine.result.Result;
import com.mc.block.constant.Post;
import com.mc.block.file.interfaces.IFileService;
import com.mc.block.img.file.web.dfs.ConnectionPool;
import com.mc.block.pojo.FileAttributes;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.UploadStream;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

@RestController
public class FileContrller {
    @Reference
    private IFileService fileService;

    private FileAttributes provingFile(MultipartFile file) {
        String contentType = file.getContentType();

        if(StringUtils.isEmpty(contentType)){
            throw new RuntimeException("File type not recognized");
        }
        String filename = file.getOriginalFilename();
        String ext = FileUtils.getExt(filename);
        if(!FileUtils.provingImgExt(ext)){
            throw new RuntimeException("Unsupported file extension");
        }

        return new FileAttributes().setName(filename.substring(0, filename.lastIndexOf("."))).setExt(ext).setType(contentType);
    }

    /**
     *
     * @param multipartFile
     * @return
     * @throws IOException
     * @throws MyException
     * @throws NoSuchAlgorithmException
     */
    @PostMapping("upload")
    public Result upload(@RequestParam("file") MultipartFile multipartFile) throws Exception, MyException, NoSuchAlgorithmException {
        FileAttributes fileAttributes = provingFile(multipartFile);
        String sha1 = FileUtils.fileSha1(multipartFile);
        FileAttributes t = fileService.findBySha1(sha1);
        if(t == null){
            InputStream inputStream = null;
            ConnectionPool connectionPool = null;
            try {
                connectionPool = ConnectionPool.getConnectionPool();
                inputStream = multipartFile.getInputStream();
                StorageClient1 storageClient1 = connectionPool.checkout(5000);
                NameValuePair[] nvp = new NameValuePair[] {
                        new NameValuePair("name", fileAttributes.getName()),
                        new NameValuePair("ext", fileAttributes.getExt()),
                        new NameValuePair("type", fileAttributes.getType())
                };
                String[] upload = storageClient1.upload_file(null, multipartFile.getSize(), new UploadStream(inputStream, multipartFile.getSize()), fileAttributes.getExt(), nvp);
                connectionPool.checkin(storageClient1);
                fileAttributes.setSha1(sha1);
                fileAttributes.setGroup(upload[0]);
                fileAttributes.setPath(upload[1]);
                fileService.upload(fileAttributes);
            } catch (Exception e) {
                if(connectionPool!=null && fileAttributes.getGroup()!=null) {
                    StorageClient1 storageClient1 = connectionPool.checkout(5000);
                    storageClient1.delete_file(fileAttributes.getGroup(), fileAttributes.getPath());
                    connectionPool.checkin(storageClient1);
                }
            } finally {
                if(inputStream != null){
                    inputStream.close();
                }
            }
        } else {
            fileAttributes = t;
        }
        return ResultUtil.success(Post.CREATED, "/" + fileAttributes.getGroup() + "/" + fileAttributes.getPath());
    }

    @GetMapping("test")
    public Result test(){
        return ResultUtil.success("ok");
    }

}
