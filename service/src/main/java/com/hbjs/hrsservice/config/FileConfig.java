package com.hbjs.hrsservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "upload")
@Configuration
@Getter
@Setter
public class FileConfig {

    private String realPath;

    private String relativePath;

}
