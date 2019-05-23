package com.notification.service.Impl;
import com.notification.queue.QueueStore;
import com.notification.request.Notification;
import com.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mailServiceImpl")
@Slf4j
public class MailServiceImpl implements NotificationService<Notification> {
    @Autowired
    private NotificationService massageServiceImpl;

    @Override
    public void send(Notification notification)throws Exception{

       if(notification.getMail()!=null){
           notification.getMail().setMailId(notification.getId());
           QueueStore.publish(notification.getMail());
           log.info("mail send successfully for mail id  "+notification.getMail().getMailAddress());
       }
         massageServiceImpl.send(notification);
    }
}
