package com.itacademy.service.config;

import com.itacademy.database.config.PersistenceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.itacademy.service")
@EnableAspectJAutoProxy
@Import(PersistenceConfig.class)
public class ServiceConfig {
}
