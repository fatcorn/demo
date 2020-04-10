package com.den.kafakproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;


import static com.den.kafakproducer.constant.KafkaTopic.TOPIC_TEST;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class KafakProducerApplication {


    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext run = SpringApplication.run(KafakProducerApplication.class, args);
        // 这里通过容器获取，正常使用情况下，可以直接使用 Autowired 注入
        KafkaTemplate kafkaTemplate = run.getBean(KafkaTemplate.class);
        int i = 0;
        while(true) {
            kafkaTemplate.send(TOPIC_TEST,"hello world:" + i);
            System.out.println("消息已发送");
            Thread.sleep(500);
            i++;
        }
    }

}
