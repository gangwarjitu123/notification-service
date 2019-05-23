package com.notification.queue;

import com.notification.request.Notification;
import lombok.experimental.UtilityClass;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
@UtilityClass
public class QueueStore {
    public  BlockingDeque<Notification.Message> messageBlockingDeque= new LinkedBlockingDeque<>();
    public  BlockingDeque<Notification.Mail> mailMessagingQueue= new LinkedBlockingDeque<>();

    public boolean publish(Notification.Message message){
          messageBlockingDeque.add(message);
          return true;
    }


    public boolean publish(Notification.Mail mail){
        mailMessagingQueue.add(mail);
        return true;
    }
}
