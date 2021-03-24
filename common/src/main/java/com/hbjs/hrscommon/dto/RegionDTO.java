package com.hbjs.hrscommon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "区域信息DTO实体")
public class RegionDTO {

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "父级ID")
    private Long pid;

    @Schema(title = "区域名称")
    private String name;

    @Schema(title = "区域编码")
    private String code;

    @Schema(title = "层级")
    private Integer level;

    @Schema(title = "下级区域")
    private List<RegionDTO> children;

}
