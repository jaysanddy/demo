package com.vk.util;

import com.vk.request.Project;
import sun.misc.BASE64Decoder;

import org.apache.commons.codec.binary.Base64;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by wj on 18-5-10.
 */
public class FileUtil {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static String base64ToFile(String destPath,String base64) {
        String filePath = destPath+"uploadFile/ysyb/";
        String timeStamp=(new Date()).getTime()+"";
        File dir = new File(filePath);
        Integer ranNum = DataUtil.getRandm(1000,9999);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.decodeBase64(base64);
            FileOutputStream write = new FileOutputStream(new File(filePath+timeStamp+""+ranNum+".jpg"));
            write.write(bytes);
            write.close();
            return "uploadFile/ysyb/"+timeStamp+ranNum+".jpg";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 删除文件.
     *
     * @param fileDir 文件路径
     */
    public static boolean delete(String fileDir) {
        boolean flag = false;
        File file = new File(fileDir);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                file.delete();
                flag = true;
            }
        }
        return flag;
    }
}
