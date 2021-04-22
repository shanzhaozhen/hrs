package com.hbjs.hrscommon.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(title = "文件名称")
    private String name;

    @Schema(title = "文件后缀")
    private String suffix;

    @Schema(title = "文件类型")
    private String type;

    @Schema(title = "文件路径")
    private String path;

    @Schema(title = "访问路径")
    private String urlPath;

    @Schema(title = "md5")
    private String md5;

}
