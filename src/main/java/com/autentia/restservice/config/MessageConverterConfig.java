package com.autentia.restservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

@Configuration
@EnableSpringDataWebSupport
public class MessageConverterConfig {
    @Bean
    public MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter() {
        return new MappingJackson2XmlHttpMessageConverter(
                new Jackson2ObjectMapperBuilder()
                        .defaultUseWrapper(false)
                        .createXmlMapper(true)
                        .build()
        );
    }
}
