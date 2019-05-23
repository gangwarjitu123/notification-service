package com.notification.controller;
import com.notification.request.SubscribeRequest;
import com.notification.response.Response;
import com.notification.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * The type Subscription controller.
 */
@RestController
@RequestMapping("/client")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;
    @PostMapping("/subscribe")
    public ResponseEntity<Object> subscribeService(@Valid @RequestBody SubscribeRequest request){
        Response subscriptionResponse= subscriptionService.subscribe(request);
        return new ResponseEntity<>(subscriptionResponse, HttpStatus.OK) ;
    }
}
