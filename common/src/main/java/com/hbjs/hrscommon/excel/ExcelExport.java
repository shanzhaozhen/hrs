package com.hbjs.hrscommon.excel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用于EasyExcel导出封装的实体，方便导出多Sheet的情况")
public class ExcelExport<T> {

    @Schema(title = "导入导出对象的class")
    Class<T> tClass;

    @Schema(title = "导入导出的数据")
    List<T> data;

    @Schema(title = "导入导出的sheet名称")
    String sheetName;

}
