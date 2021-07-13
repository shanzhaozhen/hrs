package com.hbjs.hrscommon.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BaseExcelListener<T> extends AnalysisEventListener<T> {

    private List<T> rows = new ArrayList<>();

    public List<T> getRows() {
        return rows;
    }

    @Override
    public void invoke(T object, AnalysisContext context) {
        rows.add(object);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("获取excel数据完成：一共{}行数据", rows.size());
    }
}
