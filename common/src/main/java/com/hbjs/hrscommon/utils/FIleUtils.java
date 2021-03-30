package com.hbjs.hrscommon.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;

@Slf4j
public class FIleUtils {

    /**
     * InputStream转byte数组
     * @param inputStream
     * @return
     */
    public static byte[] inputStreamToByte(InputStream inputStream) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            int len;
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("文件转换二进制文件失败");
        }
    }

    /**
     * 读取文件文本
     * @param inputStream
     * @return
     */
    public static String readFileText(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            String line = bufferedReader.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\r\n");
                line = bufferedReader.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("文件读取文本失败");
        }
    }

    public static String getFileMD5String(MultipartFile file) {
        try {
            MessageDigest mMessageDigest = null;
            mMessageDigest = MessageDigest.getInstance("MD5");
            InputStream fis = file.getInputStream();
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) > 0) {
                mMessageDigest.update(buffer, 0, length);
            }
            fis.close();
            return new BigInteger(1, mMessageDigest.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
