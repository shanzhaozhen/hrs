package com.hbjs.hrscommon.dto;

import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "文件DTO实体")
public class FileDTO extends BaseInfo {

    private static final long serialVersionUID = 1929215797997698724L;

    @Schema(title = "主键ID")
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
