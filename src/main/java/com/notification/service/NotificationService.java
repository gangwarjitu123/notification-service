package com.notification.service;

public interface NotificationService<T> {
     void send(T t) throws Exception;
}
