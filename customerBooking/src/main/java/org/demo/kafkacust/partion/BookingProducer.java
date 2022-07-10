package org.demo.kafkacust.partion;

import java.time.LocalDate;
import java.util.Properties;
import java.util.UUID;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class BookingProducer{
        public static void main(String[] args) {
    String topicName = "demo_java";

    Properties props = new Properties();
    props.put("bootstrap.servers", "127.0.0.1:9092");
    // we need to define the key serializer here  props.put("serializer.class", "kafka.serializer.DefaultEncoder");


            props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.demo.kafkacust.partion.BookingSerializer");
            props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());



            // here we are using the partitioner we created
    props.put("partitioner.class", "org.demo.kafkacust.partion.CustomPartitioner");

    Producer<BookingKey , String> producer = new KafkaProducer<>(props);


    for (int i = 0; i < 10; i++) {
        String rannumb = UUID.randomUUID().toString();
         String curtDate = LocalDate.now().toString();
        BookingKey bookingkey = new BookingKey(rannumb,curtDate);

//        String value = "value" +rannumb;
        ProducerRecord<BookingKey, String> producerRecord =
                new ProducerRecord<>(topicName,new BookingKey(rannumb,curtDate),rannumb);
        producer.send(producerRecord);
    }
    producer.close();
}
}
