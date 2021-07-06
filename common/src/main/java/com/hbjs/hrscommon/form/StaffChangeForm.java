package com.hbjs.hrscommon.form;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Schema(description = "调动记录Form实体")
public class StaffChangeForm {

    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "id不能为空")
    private Long id;

    @Schema(title = "员工id")
    @NotNull(groups = {Insert.class, Update.class}, message = "员工不能为空")
    private Long staffId;

    @Schema(title = "变更前部门id")
//    @NotEmpty(groups = {Insert.class, Update.class}, message = "变更前部门不能为空")
    private Long preDepId;

    @Schema(title = "变更后部门id")
//    @NotEmpty(groups = {Insert.class, Update.class}, message = "变更后部门不能为空")
    private Long postDepId;

    @Schema(title = "变更前职务")
    private String preDuty;

    @Schema(title = "变更后职务")
    private String postDuty;

    @Schema(title = "变更前岗位")
    private String prePost;

    @Schema(title = "变更后岗位")
    private String postPost;

    @Schema(title = "变更前岗位类型")
    private String prePostType;

    @Schema(title = "变更后岗位类型")
    private String postPostType;

    @Schema(title = "变更前岗位等级")
    private String prePostLevel;

    @Schema(title = "变更后岗位等级")
    private String postPostLevel;

    @Schema(title = "生效日期")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date effectiveDate;

    @Schema(title = "备注")
    private String remarks;

}
