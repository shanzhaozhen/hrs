package com.hbjs.hrscommon.form;

import com.hbjs.hrscommon.domain.BaseInfo;
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
@Schema(description = "教育经历Form实体")
public class EducationalExperienceForm extends BaseInfo {

    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "家庭成员id不能为空")
    private Long id;

    @Schema(title = "关联id")
    @NotEmpty(groups = {Insert.class, Update.class}, message = "关联id不能为空")
    private Long pid;

    @Schema(title = "学校")
    private String schoolName;

    @Schema(title = "开始日期")
    private Date startDate;

    @Schema(title = "结束日期")
    private Date endDate;

    @Schema(title = "学历")
    private Date education;

    @Schema(title = "专业")
    private Date major;

    @Schema(title = "学制")
    private Integer studyYears;

    @Schema(title = "是否全日制")
    private Boolean fullTime;

    @Schema(title = "证明人姓名")
    private String witnessName;

    @Schema(title = "证明人电话")
    private String witnessPhone;

}
