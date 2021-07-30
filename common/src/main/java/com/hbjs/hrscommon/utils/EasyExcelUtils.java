package com.hbjs.hrscommon.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hbjs.hrscommon.excel.ExcelExport;
import com.hbjs.hrscommon.excel.BaseExcelListener;
import com.hbjs.hrscommon.excel.ExcelImport;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

    public static <T> void exportExcel(List<ExcelExport<?>> excelExportList, String fileName) {
        ExcelWriter excelWriter = null;
        try {
            HttpServletResponse httpServletResponse = HttpServletUtils.getHttpServletResponse();
            httpServletResponse.setContentType("application/vnd.ms-excel");
            httpServletResponse.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            httpServletResponse.setHeader("Content-disposition", "attachment;filename*=utf-8''" + CommonUtils.URLFileNameConverter(fileName) + ".xlsx");
            excelWriter = EasyExcel.write(httpServletResponse.getOutputStream()).build();
            for (ExcelExport<?> excelExport: excelExportList) {
                WriteSheet writeSheet = EasyExcel.writerSheet(excelExport.getSheetName()).head(excelExport.getTClass()).build();
                excelWriter.write(excelExport.getData(), writeSheet);
            }
        } catch (IOException e) {
            Assert.notNull(e, "导出失败");
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 从Excel中读取文件，读取的文件是一个DTO类，该类必须继承BaseRowModel
     * 具体实例参考 ： MemberMarketDto.java
     * 参考：https://github.com/alibaba/easyexcel
     * 字符流必须支持标记，FileInputStream 不支持标记，可以使用BufferedInputStream 代替
     * BufferedInputStream bis = new BufferedInputStream(new FileInputStream(...));
     */


    public static <T> List<T> readExcel(final File file, final Class<?> clazz) {
        return readExcel(file, clazz, 0, null);
    }

    public static <T> List<T> readExcel(final File file, final Class<?> clazz, Integer sheetNo) {
        try (InputStream inputStream = new FileInputStream(file)) {
            return readExcel(inputStream, clazz, sheetNo, null, null);
        } catch (IOException e) {
            throw new IllegalArgumentException("文件为空或读取失败：" + e.getMessage());
        }
    }

    public static <T> List<T> readExcel(final File file, final Class<?> clazz, Integer sheetNo, String sheetName) {
        try (InputStream inputStream = new FileInputStream(file)) {
            return readExcel(inputStream, clazz, sheetNo, sheetName, null);
        } catch (IOException e) {
            throw new IllegalArgumentException("文件为空或读取失败：" + e.getMessage());
        }
    }

    public static <T> List<T> readExcel(final File file, final Class<?> clazz, Integer sheetNo, String sheetName, Integer headLineMun) {
        try (InputStream inputStream = new FileInputStream(file)) {
            return readExcel(inputStream, clazz, sheetNo, sheetName, headLineMun);
        } catch (IOException e) {
            throw new IllegalArgumentException("文件为空或读取失败：" + e.getMessage());
        }
    }

    public static <T> List<T> readExcel(final InputStream inputStream, final Class<?> clazz) {
        return readExcel(inputStream, clazz, 0, null);
    }

    public static <T> List<T> readExcel(final InputStream inputStream, final Class<?> clazz, Integer sheetNo) {
        return readExcel(inputStream, clazz, sheetNo, null, null);
    }

    public static <T> List<T> readExcel(final InputStream inputStream, final Class<?> clazz, Integer sheetNo, String sheetName) {
        return readExcel(inputStream, clazz, sheetNo, sheetName, null);
    }

    public static <T> List<T> readExcel(final InputStream inputStream, final Class<?> clazz, Integer sheetNo, String sheetName, Integer headLineMun) {
        if (null == inputStream) {
            throw new NullPointerException("the inputStream is null!");
        }

        BaseExcelListener<T> listener = new BaseExcelListener<>();

        ExcelReader excelReader;
        if (headLineMun == null) {
            excelReader = EasyExcel.read(inputStream, clazz, listener).build();
        } else {
            excelReader = EasyExcel.read(inputStream, clazz, listener).headRowNumber(headLineMun).build();
        }

        if (sheetNo != null) {
            excelReader.read(EasyExcel.readSheet(sheetNo).build());
        } else {
            excelReader.read(EasyExcel.readSheet(sheetName).build());
        }

        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
        return listener.getRows();
    }

}
