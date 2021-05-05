package com.hbjs.hrscommon.utils;

import com.alibaba.excel.EasyExcel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

public class EasyExcelUtils {

    public static <T> void exportExcel(HttpServletResponse httpServletResponse, Class<T> tClass, List<T> data) throws IOException {
        exportExcel(httpServletResponse, tClass, data, "Sheet1", UUID.randomUUID().toString());
    }

    public static <T> void exportExcel(HttpServletResponse httpServletResponse, Class<T> tClass, List<T> data, String fileName) throws IOException {
        exportExcel(httpServletResponse, tClass, data, "Sheet1", fileName);
    }

    public static <T> void exportExcel(HttpServletResponse httpServletResponse, Class<T> tClass, List<T> data, String sheetName, String fileName) throws IOException {
        httpServletResponse.setContentType("application/vnd.ms-excel");
        httpServletResponse.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String newFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        httpServletResponse.setHeader("Content-disposition", "attachment;filename*=utf-8''" + newFileName + ".xlsx");
        EasyExcel.write(httpServletResponse.getOutputStream(), tClass).sheet(sheetName).doWrite(data);
    }

}
