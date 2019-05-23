package com.notification.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Notification extends Request{

    private Mail mail;
    private Message message;
    private String id;
    public  Notification(){
        id= UUID.randomUUID().toString();
    }

    @Getter
    @Setter
    public static class  Mail{
        private String mailId;
        private String text;
        private String mailAddress;
    }


    @Getter
    @Setter
    public static class Message{
        private String messageId;
        private String text;
        private BigInteger mobileNumber;
    }

}
