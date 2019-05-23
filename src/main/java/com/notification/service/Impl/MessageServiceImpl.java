package com.notification.service.Impl;

import com.notification.queue.QueueStore;
import com.notification.request.Notification;
import com.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageServiceImpl implements NotificationService<Notification> {
    @Override
    public void send(Notification notification) throws Exception {
        if(notification.getMessage()!=null){
            notification.getMessage().setMessageId(notification.getId());
            QueueStore.publish(notification.getMessage());
            log.info("message publish to queue for mobile no. "+notification.getMessage().getMobileNumber());

        }
    }
}
