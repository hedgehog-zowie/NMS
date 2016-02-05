package com.iuni.nms.ws.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
@Configuration
@ComponentScan(basePackages = {"com.iuni.nms"})
@PropertySource(value = {"classpath:nms-dbconfig.properties", "classpath:nms-config.properties"})
@ImportResource(value = "classpath:config/spring/spring-service.xml")
public class ApplicationConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
