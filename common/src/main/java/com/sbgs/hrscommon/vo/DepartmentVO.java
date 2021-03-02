package com.sbgs.hrscommon.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "部门VO实体")
public class DepartmentVO {

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "部门名称")
    private String name;

    @Schema(title = "上级ID")
    private Long pid;

}
