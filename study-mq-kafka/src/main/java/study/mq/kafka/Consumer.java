package study.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;


public class Consumer {

    public static void main(String[] args) {
        //consumer 的配置属性
        Properties props = new Properties();
        ///brokers 地址
        props.put("bootstrap.servers", "localhost:9092");
        //指定该 consumer 将加入的消费组
        props.put("group.id", "test");
        //开启自动提交 offset，关于offset提交，我们后续再来详细说说
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        //指定序列化类
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        //创建
        KafkaConsumer<String, String> consumer = new KafkaConsumer(props);
        //订阅消费主题，这里一个消费者可以同时消费 foo 和 bar 两个主题的数据
        consumer.subscribe(Arrays.asList("foo", "bar"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }
}
