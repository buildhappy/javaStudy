package com.buildhappy.kafka_study.demo;

import com.buildhappy.kafka_study.KafkaProperties;
import com.buildhappy.kafka_study.consumer.KafkaConsumer;
import com.buildhappy.kafka_study.producer.KafkaProducer;

public class KafkaConsumerProducerDemo{
    public static void main(String[] args){
        KafkaProducer producerThread = new KafkaProducer(KafkaProperties.topic);
        producerThread.start();
        KafkaConsumer consumerThread = new KafkaConsumer(KafkaProperties.topic);
        consumerThread.start();
    }
}