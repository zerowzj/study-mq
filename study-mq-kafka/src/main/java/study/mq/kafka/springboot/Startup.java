package study.mq.kafka.springboot;

import org.springframework.boot.SpringApplication;
import study.mq.kafka.springboot.support.SpringBootCfg;

public class Startup {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCfg.class, args);
    }
}
