package com.hbjs.hrscommon.domain.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.hbjs.hrscommon.domain.BaseInfo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_region")
@Schema(description = "地区信息DO实体")
public class RegionDO extends BaseInfo {

    private static final long serialVersionUID = 216445339652015543L;

    @Schema(title = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "父级ID")
    private Long pid;

    @Schema(title = "区域名称")
    private String name;

    @Schema(title = "区域编码")
    private String code;

    @Schema(title = "层级")
    private Integer level;

}
