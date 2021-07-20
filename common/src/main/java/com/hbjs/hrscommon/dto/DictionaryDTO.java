package com.hbjs.hrscommon.dto;

import com.hbjs.hrscommon.domain.BaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "字典DTO实体")
public class DictionaryDTO extends BaseInfo {

    private static final long serialVersionUID = -4736206395385385450L;

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "字典名称")
    private String name;

    @Schema(title = "字典编码")
    private String code;

    @Schema(title = "字典表达值")
    private String express;

    @Schema(title = "上级ID")
    private Long pid;

    @Schema(title = "排序等级")
    private Integer priority;

    @Schema(title = "字典描述")
    private String description;

    @Schema(title = "子成员")
    private List<DictionaryDTO> children;

    @Schema(title = "是否有子节点")
    private Boolean hasChildren;

}
