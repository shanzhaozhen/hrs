package com.hbjs.hrscommon.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户部门Form实体")
public class UserDepartmentForm {

    @Schema(title = "用户ID")
    @NotEmpty(message = "用户id不能为空")
    private List<Long> userIds;

    @Schema(title = "部门ID")
    private Long departmentId;

}
