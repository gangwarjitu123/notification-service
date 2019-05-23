package com.notification.dao.database;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 * The type Subscription data.
 * this class data  will be fetched from database in real time
 */
public final  class SubscriptionData {

    /**
     * The enum Subscription type.
     */
    public enum SubscriptionType {
        /**
         * Gold subscription type.
         */
        GOLD,
        /**
         * Silver subscription type.
         */
        SILVER,
        /**
         * Platenium subscription type.
         */
        PLATENIUM;

    }

    /**
     * The Subscription data.
     */
    static  public HashMap<SubscriptionType,SubscriptionDetails> subscriptionData= new LinkedHashMap<>();
    static  {

        subscriptionData.put(SubscriptionType.GOLD,new SubscriptionDetails(10l,true,true,1000));
        subscriptionData.put(SubscriptionType.SILVER,new SubscriptionDetails(10000l,true,false,49));
        subscriptionData.put(SubscriptionType.PLATENIUM,new SubscriptionDetails(1000000000l,true,true,10000));
    }

    /**
     * The type Subscription details.
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class SubscriptionDetails{
        private Long count;
        private boolean email;
        private boolean sms;
        private double amountPerMonth;

        /**
         * Instantiates a new Subscription details.
         *
         * @param count the count
         * @param email the email
         * @param sms   the sms
         */
        public SubscriptionDetails(Long count, boolean email, boolean sms,double amountPerMonth) {
            this.count = count;
            this.email = email;
            this.sms = sms;
            this.amountPerMonth=amountPerMonth;
        }
    }

}
