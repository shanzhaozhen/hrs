package com.hbjs.hrscommon.form;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("hr_work_experience")
@Schema(description = "工作履历DO实体")
public class WorkExperienceForm {

    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "工作经历id不能为空")
    private Long id;

    @Schema(title = "关联id")
    @NotEmpty(groups = {Insert.class, Update.class}, message = "关联id不能为空")
    private Long pid;

    @Schema(title = "工作单位")
    private String workUnit;

    @Schema(title = "开始时间")
    private Date startDate;

    @Schema(title = "结束时间")
    private Date endDate;

    @Schema(title = "职务/岗位")
    private String duty;

    @Schema(title = "单位性质")
    private String unitType;

    @Schema(title = "月薪")
    private String salary;

    @Schema(title = "证明人姓名")
    private String witnessName;

    @Schema(title = "证明人电话")
    private String witnessPhone;

}
