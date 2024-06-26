package com.ywk.sems.config;

import com.ywk.sems.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 设置静态资源映射
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射...");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor()).excludePathPatterns(
//                "/user/login", "/user/register", "/user/check", "/**/*.html",
//                "**.html", "/js/**", "/css/**", "/image*/**", "/user/avatar", "/file/upload/img");
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/user/**", "/stu/**", "/emp/**", "/comp/**")
                .excludePathPatterns("/user/login", "/user/register", "/user/check", "/user/avatar");
    }
}
