package com.cml.learn.configurable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

/**
 * @Auther: cml
 * @Date: 2018-08-02 10:00
 * @Description:
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ConfigurableTest {
    public static void main(String[] args) {
        org.springframework.context.ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ConfigurableTest.class, args);

        User user = configurableApplicationContext.getBeanFactory().getBean(User.class);
        user.save();
        System.out.println(configurableApplicationContext.getBean(User.class));
        System.out.println(configurableApplicationContext.getBean(User.class));
    }

}
