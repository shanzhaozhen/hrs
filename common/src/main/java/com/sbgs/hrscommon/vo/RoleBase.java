package com.sbgs.hrscommon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "角色主要信息实体")
public class RoleBase {

    @Schema(title = "主键ID")
    private Long id;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "标识名称")
    private String identification;

}
