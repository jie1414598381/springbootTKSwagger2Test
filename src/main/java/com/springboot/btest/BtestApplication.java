package com.springboot.btest;

import com.springboot.btest.common.AppInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 启动类
 */
@EnableTransactionManagement
@EnableScheduling
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@EnableSwagger2
@MapperScan(basePackages = "com.springboot.btest.dao")
public class BtestApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BtestApplication.class);
    }

    /**
     * 拦截器
     */
    @Configuration
    static class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(newAppInterceptor()).addPathPatterns("/**").excludePathPatterns("/swagger-ui.html")
                    .excludePathPatterns("/webjars/**",
                            "/swagger-ui.html","/swagger-resources/**",
                            "/v2/api-docs/**","/login");
        }

        @Bean
        public AppInterceptor newAppInterceptor() {
            return new AppInterceptor();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(BtestApplication.class, args);
    }
}