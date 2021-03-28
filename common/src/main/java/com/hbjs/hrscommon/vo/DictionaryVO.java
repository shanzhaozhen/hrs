package com.hbjs.hrscommon.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "字典VO实体")
public class DictionaryVO extends BaseInfoVO {

    @Schema(title = "主键ID")
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(title = "字典名称")
    private String name;

    @Schema(title = "字典编码")
    private String code;

    @Schema(title = "上级ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long pid;

    @Schema(title = "排序等级")
    private Integer priority;

    @Schema(title = "字典描述")
    private String description;

    @Schema(title = "子成员")
    private List<DictionaryVO> children;

}
