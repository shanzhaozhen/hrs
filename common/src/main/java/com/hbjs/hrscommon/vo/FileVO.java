package com.hbjs.hrscommon.vo;

import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "文件VO实体")
public class FileVO extends BaseInfo {

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "文件名称")
    private String fileName;

    @Schema(title = "文件后缀")
    private String suffixName;

    @Schema(title = "文件路径")
    private String path;

}
