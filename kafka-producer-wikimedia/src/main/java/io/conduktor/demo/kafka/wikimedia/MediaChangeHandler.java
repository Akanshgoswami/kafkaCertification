package io.conduktor.demo.kafka.wikimedia;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MediaChangeHandler implements EventHandler{

    KafkaProducer<String,String> kafkaProducer;
    String topic;
    private final Logger log = LoggerFactory.getLogger(MediaChangeHandler.class.getSimpleName());

    public MediaChangeHandler(KafkaProducer<String,String> kafkaProducer, String topic){
        this.kafkaProducer = kafkaProducer;
        this.topic = topic;
    }

    @Override
    public void onOpen()  {
//nothing here


    }

    @Override
    public void onClosed() {
        kafkaProducer.close();

    }

    @Override
    public void onMessage(String event, MessageEvent messageEvent)  {
        log.info(messageEvent.getData());
//        aynchronous
        kafkaProducer.send(new ProducerRecord<>(topic,messageEvent.getData()));
    }

    @Override
    public void onComment(String comment)  {
//        Nothing Here

    }

    @Override
    public void onError(Throwable t) {
        log.error("Error in Stream Reading",t);
    }

}
