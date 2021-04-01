package com.hbjs.hrscommon.domain.hr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("hr_department_record")
@Schema(description = "部门变更记录DO实体")
public class DepartmentRecordDO extends BaseInfo {

    private static final long serialVersionUID = -1739670411560420672L;

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(title = "员工表id")
    private Long staffId;

    @Schema(title = "变更前部门id")
    private Long preId;

    @Schema(title = "变更后部门id")
    private Long postId;

    @Schema(title = "生效日期")
    private Date effectiveDate;

}
