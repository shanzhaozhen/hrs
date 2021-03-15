package com.sbgs.hrscommon.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "字典VO实体")
public class DictionaryVO extends BaseInfoVO {

    @Schema(title = "主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "上级ID")
    private Long pid;

}
