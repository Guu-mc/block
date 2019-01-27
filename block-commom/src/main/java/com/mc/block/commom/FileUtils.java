package com.mc.block.commom;

import com.mc.block.confine.result.ImgFileType;
import com.mc.block.pojo.FileAttributes;
import org.springframework.core.io.InputStreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class FileUtils {

    /**
     * 获取文件扩展名, 无法找到返回 ""
     * @param fileName
     * @return
     */
    public static String getExt(String fileName){
        if(fileName != null){
            int i = fileName.lastIndexOf(".");
            if(i != -1){
                return fileName.substring(i + 1).toLowerCase();
            }
        }
        return "";
    }

    /**
     * 去除文件扩展名(包括 '.')
     * @param fileName
     * @return
     */
    public static String rmExt(String fileName){
        if(fileName != null){
            int i = fileName.lastIndexOf(".");
            if(i != -1){
                return fileName.substring(0, i);
            }
        }
        return "";
    }

    /**
     * 校验是否接收图片文件类型
     * @param ext
     * @return
     */
    public static boolean provingImgExt(String ext){
        try {
            ImgFileType.valueOf(ext);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static File saveTempFile(InputStream is, FileAttributes fileAttributes) throws IOException {
        File tempFile;
        FileOutputStream os = null;
        try {
            tempFile = File.createTempFile(fileAttributes.getName() + new Date().getTime(), "." + fileAttributes.getExt());
            os = new FileOutputStream(tempFile);
            byte[] bt = new byte[1024];
            int ch;
            while ((ch = is.read(bt)) != -1){
                os.write(bt, 0, ch);
            }

        } catch (Exception e) {
            throw new RuntimeException("无法创建临时文件");
        } finally {
            if(os != null){
                os.close();
            }
        }
        return tempFile;
    }

    /**
     * 计算文件sha1
     * @param inputStreamSource
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static String fileSha1(InputStreamSource inputStreamSource) throws IOException, NoSuchAlgorithmException {
        InputStream inputStream = null;
        try {
            inputStream = inputStreamSource.getInputStream();
            return fileSha1(inputStream);
        } finally {
            if(inputStream!=null) {
                inputStream.close();
            }
        }
    }

    /**
     * 计算文件sha1
     * @param is
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static String fileSha1(InputStream is) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] buff = new byte[8192];
        int ch;
        while ((ch = is.read(buff)) != -1){
            digest.update(buff, 0, ch);
        }
        String sha1 = new BigInteger(1, digest.digest()).toString(16);
        int length = 40 - sha1.length();
        if (length > 0) {
            for (int i = 0; i < length; i++)
            {
                sha1 = "0" + sha1;
            }
        }
        return sha1;
    }

    /**
     * 移动文件
     * @param oldPath
     * @param newPath
     */
    public static void mvFile(String oldPath, String newPath){
        File file = new File(oldPath);
        if(file.exists()){
            file.compareTo(new File(newPath));
        }
    }

}
