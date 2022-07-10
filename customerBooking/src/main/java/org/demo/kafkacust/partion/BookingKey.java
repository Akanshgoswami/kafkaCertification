package org.demo.kafkacust.partion;

import java.time.LocalDate;

public class BookingKey {
    private String cutomerID;
    private String bookingId;


    public BookingKey(String customerId, String bookingId) {
        this.cutomerID =customerId;
        this.bookingId=bookingId;
    }

    public String getCutomerID() {
        return cutomerID;
    }

    public String getBookingId() {
        return  bookingId;
    }

    @Override
    public String toString(){
        return "BookingKey{" + "CustomerId='" +cutomerID + ", Bookingdate=" + bookingId + '}' ;

    }


}
