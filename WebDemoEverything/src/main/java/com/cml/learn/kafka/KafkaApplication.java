package com.cml.learn.kafka;

import com.cml.learn.swagger2.SpringbootSwagger2Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@ImportResource("classpath:/application-kafka.yml")
@EnableKafka
public class KafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootSwagger2Application.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            kafkaTemplate.send("someTopic", "testmsg");
        };
    }

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @KafkaListener(topics = "someTopic")
    public void processMessage(String content) {
        System.out.println("====receive====>" + content);
    }
}
