package com.minio.file.config;

import com.fasterxml.classmate.TypeResolver;
import com.minio.file.domain.SysFileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 23:05 2022/7/23
 * @ Modified By：
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Autowired
    TypeResolver typeResolver;

    @Bean
    public Docket baseRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("com.minio.file"))
                .paths(PathSelectors.any())
                .build()
                .additionalModels(typeResolver.resolve(SysFileInfo.class));
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("文件操作-API文档")
                .description("minio接口文档Swagger版")
                .contact(new Contact("小问号", "https://github.com/xiaowenhaohub", "571375739@qq.com"))
                .version("1.0")
                .build();
    }

}
