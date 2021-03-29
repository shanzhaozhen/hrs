package com.hbjs.hrsapi.config.mvc;

import com.hbjs.hrsservice.config.FileConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class CustomWebMvcConfig implements WebMvcConfigurer {

    private final FileConfig fileConfig;

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
////        registry.addViewController("/login").setViewName("public/login");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);                                  //过滤时优先执行
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler(fileConfig.getRelativePath() + "**").addResourceLocations("file:" + fileConfig.getRealPath());
//        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        // 解决 SWAGGER 404报错
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
