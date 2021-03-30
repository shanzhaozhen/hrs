package com.hbjs.hrscommon.domain.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_file")
@Schema(description = "文件DO实体")
public class FileDO extends BaseInfo {

    private static final long serialVersionUID = 3101276956474117067L;

    @Schema(title = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
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
