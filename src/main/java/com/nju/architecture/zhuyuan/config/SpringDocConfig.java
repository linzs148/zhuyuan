package com.nju.architecture.zhuyuan.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring doc配置
 */
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI restfulOpenAPI() {
        return new OpenAPI()
          .info(new Info().title("筑园项目接口文档")
            .description("筑园项目接口文档")
            .version("v1.0.0")
            .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

}
