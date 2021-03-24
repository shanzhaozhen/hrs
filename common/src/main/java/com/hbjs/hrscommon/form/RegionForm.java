package com.hbjs.hrscommon.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "区域信息Form实体")
public class RegionForm {

    @Schema(title = "主键ID")
    @NotNull(groups = {Update.class}, message = "资源id不能为空")
    private Long id;

    @Schema(title = "父级ID")
    private Long pid;

    @Schema(title = "区域名称")
    @NotEmpty(groups = {Insert.class, Update.class}, message = "区域名称不能为空")
    private String name;

    @Schema(title = "区域编码")
    @NotEmpty(groups = {Insert.class, Update.class}, message = "区域编码不能为空")
    private String code;

    @Schema(title = "层级")
    @NotNull(groups = {Insert.class, Update.class}, message = "层级不能为空")
    private Integer level;

}
