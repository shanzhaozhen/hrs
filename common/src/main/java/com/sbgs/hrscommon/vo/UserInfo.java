package com.sbgs.hrscommon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户登陆信息")
public class UserInfo {

    @Schema(title = "昵称")
    private String nickname;

    @Schema(title = "头像")
    private String avatar;

    @Schema(title = "个人简介")
    private String introduction;

}
