package com.notification.dao.database;
import lombok.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * The type Client data.
 * this will work as database
 */
public class ClientData {
    /**
     * key=clinetid
     * Client=Data
     */
    static public HashMap<String,Client> clients= new LinkedHashMap<>();;
    static  {

        clients.put("e92674c2-54da-441b-aa35-40dd8ae73e6f",new Client("JITENDRA","JITENDRA123","Jitendra", Collections.singletonList(
                new PlanDetails(SubscriptionData.SubscriptionType.GOLD,1,1*99,0))));
    }

    /**
     * The type Client.
     */
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
     @NoArgsConstructor
     public static class Client{
        private String clientName;
        private String clientUserName;
        private String clientPassword;
        private List<PlanDetails> subsDetails;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PlanDetails{
        private SubscriptionData.SubscriptionType subscriptionType;
        private int month;
        private double amount;
        private int currentUses;
    }
}
