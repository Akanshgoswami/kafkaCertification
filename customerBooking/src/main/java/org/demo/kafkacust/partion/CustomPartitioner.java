package org.demo.kafkacust.partion;

import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;

import java.time.LocalDate;
import java.util.Objects;

public class CustomPartitioner extends DefaultPartitioner {

    public int partition(String topic, Object key, byte[] keyBytes,
                         Object value, byte[] valuesBytes, Cluster cluter){
        String partitionKey = null;
        if(Objects.nonNull(key)) {

            BookingKey bookingKey = (BookingKey) key;
            partitionKey = bookingKey.getCutomerID();
            keyBytes = partitionKey.getBytes();
        }
        return super.partition(topic,partitionKey,keyBytes,value,valuesBytes,cluter);
    }


}
