package com.buildhappy.kafkastudy.demo;

import com.buildhappy.kafkastudy.KafkaProperties;
import com.buildhappy.kafkastudy.consumer.KafkaConsumer;
import com.buildhappy.kafkastudy.producer.KafkaProducer;

public class KafkaConsumerProducerDemo{
    public static void main(String[] args){
        KafkaProducer producerThread = new KafkaProducer(KafkaProperties.topic);
        producerThread.start();
        KafkaConsumer consumerThread = new KafkaConsumer(KafkaProperties.topic);
        consumerThread.start();
    }
}