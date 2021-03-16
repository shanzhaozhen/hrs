package com.sbgs.hrscommon.domain.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sbgs.hrscommon.domain.BaseInfo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_department")
@Schema(description = "部门DO实体")
public class DepartmentDO extends BaseInfo {

    private static final long serialVersionUID = -4727379501712632270L;

    @Schema(title = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(title = "部门名称")
    private String name;

    @Schema(title = "部门编号")
    private String code;

    @Schema(title = "上级ID")
    private Long pid;

    @Schema(title = "排序等级")
    private Integer priority;

}
