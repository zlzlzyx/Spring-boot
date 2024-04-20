package com.zhanglu.bootruan78.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sun.security.krb5.internal.PAData;
/**
 * @Author zhanglu
 * @StudentId 2011050712
 * class 7
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
//      java  配置 跨域请求的设置
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")/*所有的当前站点的请求地址，都支持跨域访问*/
                .allowedOrigins("*")/*所有的外部域都可跨域访问*/
                //.allowedOriginPatterns("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")/*哪些请求 需要跨域配置*/
                .allowCredentials(false) /*是否支持跨域用户凭证*/
                .maxAge(3600)/*超时时长设置为6分钟。 时间单位是秒。*/
                .allowedHeaders("*");/*请求体的头部*/
    }
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
