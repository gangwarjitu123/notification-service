package com.notification.utils;

import com.notification.dao.database.ClientData;
import com.notification.exception.CustomNotificationException;
import com.notification.request.Notification;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;


/**
 * The type Validator.
 * Utility class
 */
@UtilityClass
public class Validator {


    public void validateClientAndRequest(Notification notification, String clientKey) {
        if (notification == null) {
            throw new CustomNotificationException("mail or message should be present in request",
                    HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
        }
        if (clientKey == null || ClientData.clients.get(clientKey) == null) {
            throw new CustomNotificationException("authentication failed : client key missing or invalid",
                    HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
        }
    }
}

