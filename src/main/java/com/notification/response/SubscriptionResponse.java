package com.notification.response;

import static  com.notification.dao.database.ClientData.PlanDetails;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
@Getter
@Setter
@Builder
public class SubscriptionResponse  {
   private String clientKey;
   private String clientName;
   private List<PlanDetails> subscribePlanDetails;
}
