package com.notification.service;

import com.notification.request.SubscribeRequest;
import com.notification.response.Response;

public interface SubscriptionService {
     Response subscribe(SubscribeRequest subscribeRequest);
}
