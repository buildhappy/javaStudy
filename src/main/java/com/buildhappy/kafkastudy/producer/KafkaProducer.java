package com.buildhappy.kafkastudy.producer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;

/**
 * kafka的生产者
 * Created by buildhappy on 15/9/18.
 */
public class KafkaProducer extends Thread {
    private final Log log = LogFactory.getLog(getClass().getName());
    private final Producer<Integer , String> producer;
    private String topic;
    private Properties props = new Properties();

    public KafkaProducer(String topic){
        props.put("serializer.class" , "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list" , "10.22.10.139:9092");//broker地址
        producer = new Producer<Integer, String>(new ProducerConfig(props));
        this.topic = topic;
    }

    public void run() {
        int mesNum = 0;
        while(true){
            String mes = "message_" + mesNum++;
            KeyedMessage<Integer , String> keyedMessage = new KeyedMessage<Integer, String>(topic , mes);
            producer.send(keyedMessage);
            log.info("Produce message:" + mes);
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                log.info("Thread Interrupeted");
            }
        }
    }
}
