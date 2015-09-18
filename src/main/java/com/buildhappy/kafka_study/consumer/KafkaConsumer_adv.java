package com.buildhappy.kafka_study.consumer;

import com.buildhappy.kafka_study.KafkaProperties;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 比较负载的发送接收的程序
 * Created by buildhappy on 15/9/18.
 */
public class KafkaConsumer_adv extends Thread {
    private final ConsumerConnector consumer;
    private final String topic;

    public KafkaConsumer_adv(String topic){
        this.topic = topic;
        consumer = Consumer.createJavaConsumerConnector(createConsumerConfig());
    }
    private static ConsumerConfig createConsumerConfig(){
        Properties props = new Properties();
        props.put("zookeeper.connect" , KafkaProperties.zkConnect);
        props.put("group.id" , KafkaProperties.groupId);
        props.put("zookeeper.session.timeout.ms" , "40000");
        props.put("zookeeper.sync.time.ms" , "200");
        props.put("auto.commit.interval.ms" , "1000");
        return new ConsumerConfig(props);
    }
    public void run(){
        Map<String , Integer> topicCountMap = new HashMap<String , Integer>();
        topicCountMap.put(topic , 1);
        Map<String , List<KafkaStream<byte[] , byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[] , byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while(it.hasNext()){
            System.out.println(new String(it.next().message()));
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
