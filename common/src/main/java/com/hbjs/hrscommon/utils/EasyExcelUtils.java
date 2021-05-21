package com.hbjs.hrscommon.utils;

import com.alibaba.excel.EasyExcel;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Log4j2
public class EasyExcelUtils {

    public static <T> void exportExcel(Class<T> tClass, List<T> data) {
        exportExcel(tClass, data, "Sheet1", UUID.randomUUID().toString());
    }

    public static <T> void exportExcel(Class<T> tClass, List<T> data, String fileName) {
        exportExcel(tClass, data, "Sheet1", fileName);
    }

    public static <T> void exportExcel(Class<T> tClass, List<T> data, String sheetName, String fileName) {
        try {
            HttpServletResponse httpServletResponse = HttpServletUtils.getHttpServletResponse();
            httpServletResponse.setContentType("application/vnd.ms-excel");
            httpServletResponse.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            httpServletResponse.setHeader("Content-disposition", "attachment;filename*=utf-8''" + CommonUtils.URLFileNameConverter(fileName) + ".xlsx");
            EasyExcel.write(httpServletResponse.getOutputStream(), tClass).sheet(sheetName).doWrite(data);
        } catch (IOException e) {
            Assert.notNull(e, "导出失败");
        }
    }

}
