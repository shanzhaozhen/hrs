package com.sbgs.hrscommon.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sbgs.hrscommon.dto.TreeModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "部门VO实体")
public class DepartmentVO extends BaseInfoVO implements TreeModel {

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "部门名称")
    private String name;

    @Schema(title = "部门编号")
    private String code;

    @Schema(title = "上级ID")
    private Long pid;

    @Schema(title = "排序等级")
    private Integer priority;

    @Schema(title = "下级部门")
    private List<DepartmentVO> children;

    @Override
    public void setChildren(List<? extends TreeModel> children) {
        this.children = (List<DepartmentVO>) children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public List<DepartmentVO> getChildren() {
        return children;
    }

}
