package ru.nazarfatichov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;

@Configuration
@ComponentScan(value = "ru.nazarfatichov")
public class RootConfiguration {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUsername("exam_admin");
        driverManagerDataSource.setPassword("123");
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/exam_refactoring");
        return driverManagerDataSource;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("");
        return freeMarkerConfigurer;
    }

    @Bean
    public ViewResolver viewResolver(){
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".ftl");
        return viewResolver;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
//        <property name="username" value="exam_admin"/>
//        <property name="password" value="123"/>
//        <property name="driverClassName" value="org.postgresql.Driver"/>
//        <property name="url" value="jdbc:postgresql://localhost:5432/exam_refactoring"/>
//    </bean>
//
//    <bean id="freemarkerConfig" class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer">
//        <property name="templateLoaderPath" value=""/>
//    </bean>
//
//    <bean id="viewResolver" class="org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver">
//        <property name="prefix" value="/templates/"/>
//        <property name="suffix" value=".ftl"/>
//    </bean>
//    <bean id="passwordEncoder" class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"/>
//
//    <mvc:annotation-driven/>
//    <mvc:resources mapping="/css/**" location="/css/"/>
//    <mvc:resources mapping="/js/**" location="/js/"/>
//    <mvc:resources mapping="/images/**" location="/images/"/>



}
