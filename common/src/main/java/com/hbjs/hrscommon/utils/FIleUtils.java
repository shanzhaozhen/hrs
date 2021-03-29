package com.hbjs.hrscommon.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

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

}
