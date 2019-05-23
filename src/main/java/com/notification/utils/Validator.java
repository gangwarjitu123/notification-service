package com.notification.utils;

import com.notification.dao.database.ClientData;
import com.notification.dao.database.SubscriptionData;
import com.notification.exception.CustomNotificationException;
import com.notification.request.Notification;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.List;


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

    public void checkEligibilityAndUpdate(Notification notification, String clientKey) {
        ClientData.Client client = ClientData.clients.get(clientKey);
        List<ClientData.PlanDetails> planDetailsList = client.getSubsDetails();
        ClientData.PlanDetails planDetail = planDetailsList.get(0);
        if (planDetailsList.size() == 1) {
            if (StringUtils.endsWithIgnoreCase(SubscriptionData.SubscriptionType.GOLD.name(),
                    planDetail.getSubscriptionType().name())) {
                planDetail.setCurrentUses(planDetail.getCurrentUses() + 1);

            } else {
                SubscriptionData.SubscriptionDetails subscriptionDetails =
                        SubscriptionData.subscriptionData.get(planDetailsList.get(0).getSubscriptionType());
                planDetail.setCurrentUses(planDetail.getCurrentUses() + 1);
                Long count = subscriptionDetails.getCount();
                if (count < planDetail.getCurrentUses()) {
                    throw new CustomNotificationException("subscriber details over", HttpStatus.BANDWIDTH_LIMIT_EXCEEDED.value()
                            , HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
                }
            }
        }
    }
}

