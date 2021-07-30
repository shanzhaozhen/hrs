package com.hbjs.hrscommon.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "字典Form实体")
public class DictionaryForm {

    @Schema(title = "主键ID")
    @NotEmpty(groups = {Update.class}, message = "字典ID不能为空")
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

}