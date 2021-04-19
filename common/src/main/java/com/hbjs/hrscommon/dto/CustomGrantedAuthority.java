package com.hbjs.hrscommon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "自定义权限")
public class CustomGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = -6266262925253221539L;

    private String role;

    @Override
    public String getAuthority() {
        return role;
    }

}
