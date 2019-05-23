package com.notification.subscriber;
import com.notification.queue.QueueStore;
import com.notification.request.Notification;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationSubscriber {

     Thread messageSubscriberThread= new Thread(new MessageSubscriber());
     Thread mailSubscriber= new Thread(new MailSubscriber());

    @EventListener
    public void handleContextRefreshEvent(ContextStartedEvent ctxStartEvt) {
        messageSubscriberThread.start();
        mailSubscriber.start();
    }
    public class MessageSubscriber implements Runnable{
        /**
         * this blocking queue so we don't need synchronization manually
         */
        @Override
        public void run() {
          while (true){
                  Notification.Message message=QueueStore.messageBlockingDeque.remove();
                  log.info("Message process successfully :" + message);
          }
        }
    }


    public class MailSubscriber implements Runnable{

        @Override
        public void run() {
            /**
             * this blocking queue so we don't need synchronization manually
             */
            while (true){
                Notification.Mail mail=QueueStore.mailMessagingQueue.remove();
                log.info("Message process successfully :" + mail);
            }
        }
    }
}
