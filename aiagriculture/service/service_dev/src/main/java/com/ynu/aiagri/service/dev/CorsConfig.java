package com.ynu.aiagri.service.dev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration configuration = new CorsConfiguration();
        // 是否允许请求带有验证信息
        configuration.setAllowCredentials(true);
        // 允许访问的客户端域名
        configuration.addAllowedOrigin("*");
        // 允许服务端访问的客户端请求头
        configuration.addAllowedHeader("*");
        // 允许访问的方法名,GET POST等
        configuration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }
}

