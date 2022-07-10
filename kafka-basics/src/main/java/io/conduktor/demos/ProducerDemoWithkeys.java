package io.conduktor.demos;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithkeys {
 private static  final Logger log = LoggerFactory.getLogger(ProducerDemoWithkeys.class.getSimpleName());

    public static void main(String[] args) {

     log.info("Kafka Call back");
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        KafkaProducer<String,String> producer = new KafkaProducer<>(properties);

        for (int i = 32; i<=55; i++) {

            String topic = "demo_java";
            String value = "Hwllo-World" + i;
            String key ="id_"+i;


            ProducerRecord<String, String> producerRecord =
                    new ProducerRecord<>(topic,key,value);
//        Send data

            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e == null){
                    log.info("Topic Name"+metadata.topic() +"\n" +
                            "Offset: \t"+metadata.offset() + "\n" +
                            "Key: \t" + producerRecord.key() + "\n" +
                            "Partition Name : \t" + metadata.partition());
                }else{
                       log.error("Error while producing message",e);
                    }

                }
            });
            try{
                Thread.sleep(100);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        producer.flush();

     producer.close();


    }
}
