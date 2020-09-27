package study.mq.kafka.springboot.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyListener {

    @KafkaListener(topics = "cicada-topic")
    public void listenMsg(ConsumerRecord<String, String> record) {
        String value = record.value();
        log.info(">>>>>> {}" + value);
    }
}
