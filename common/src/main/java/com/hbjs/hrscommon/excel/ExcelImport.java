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
public class ExcelImport<T> {

    @Schema(title = "导入对象的class")
    Class<T> tClass;

    @Schema(title = "导入的数据")
    List<T> data;

    @Schema(title = "导入的sheet序号")
    Integer sheetNo;

    @Schema(title = "导入的sheet名称")
    String sheetName;

    @Schema(title = "导入的表头行数")
    Integer headLineMun;

}
