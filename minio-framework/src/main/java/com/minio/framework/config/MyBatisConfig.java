package com.minio.framework.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis扫描包
 */
@Configuration
@MapperScan("com.minio.**.mapper")
public class MyBatisConfig
{

}
