package com.notification.controller;
import com.notification.dao.database.ClientData;
import com.notification.exception.CustomNotificationException;
import com.notification.request.Notification;
import com.notification.response.NotificationResponse;
import com.notification.response.Response;
import com.notification.service.NotificationService;
import com.notification.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController("/notification")
public class NotificationController {
    @Autowired
    @Qualifier("mailServiceImpl")
    private NotificationService<Notification> notificationService;
    @PostMapping("/send")
    public ResponseEntity<Object> sendNotification(@RequestBody  Notification notification, @RequestHeader("clientKey")
                                                   String clientKey){
             Validator.validateClientAndRequest(notification,clientKey);
             try {
                notificationService.send(notification);
                NotificationResponse notificationResponse=NotificationResponse.builder()
                        .message("request process successfully")
                        .requestId(notification.getRequestId())
                        .build();
                return new ResponseEntity<>( Response.builder()
                          .status("success")
                          .data(notificationResponse)
                          .build(), HttpStatus.OK);
            }catch (Exception ex){
              throw new CustomNotificationException("request process failed",
                      HttpStatus.NOT_ACCEPTABLE.value(),HttpStatus.BAD_GATEWAY);
            }

    }
}
