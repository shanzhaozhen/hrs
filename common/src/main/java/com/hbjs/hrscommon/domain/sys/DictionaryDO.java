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
@TableName("sys_dictionary")
@Schema(description = "字典DO实体")
public class DictionaryDO extends BaseInfo {

    private static final long serialVersionUID = -4727379501712632270L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "字典名称")
    private String name;

    @Schema(title = "字典编码")
    private String code;

    @Schema(title = "上级ID")
    private Long pid;

    @Schema(title = "排序等级")
    private Integer priority;

    @Schema(title = "字典描述")
    private String description;

}
