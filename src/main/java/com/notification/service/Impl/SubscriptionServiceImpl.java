package com.notification.service.Impl;
import static com.notification.dao.database.ClientData.Client;
import com.notification.dao.database.ClientData;
import com.notification.dao.database.SubscriptionData;
import com.notification.request.SubscribeRequest;
import com.notification.response.Response;
import com.notification.response.SubscriptionResponse;
import com.notification.service.SubscriptionService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;



/**
 * The type Subscription service.
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Override
    public Response subscribe(SubscribeRequest subscribeRequest) {
        /**
         * TODO need to validate plan in real time
         */
        List<ClientData.PlanDetails> planDetails =getPlanDetails(subscribeRequest);
           Client client= Client.builder()
                   .clientName(subscribeRequest.getClientName())
                   .clientPassword(subscribeRequest.getClientPassword())
                   .clientUserName(subscribeRequest.getClientUserName())
                   .subsDetails(getPlanDetails(subscribeRequest))
                   .build();
           ClientData.clients.put(subscribeRequest.getRequestId(),client);

           /*
              return response to client with subscribe details
            */
        SubscriptionResponse subscriptionResponse=  SubscriptionResponse.builder()
                  .clientKey(subscribeRequest.getRequestId())
                  .clientName(subscribeRequest.getClientName())
                  .subscribePlanDetails(planDetails)
                   .build();
        return  Response.builder().requestId(subscribeRequest.getRequestId())
                .status("success")
                /**
                 * status will be configurable
                 */
        .data(subscriptionResponse).build();

    }
     private List<ClientData.PlanDetails> getPlanDetails(SubscribeRequest subscribeRequest){
         List<SubscribeRequest.SubscribePlan> subscribePlans=subscribeRequest.getSubscribePlan();
         List<ClientData.PlanDetails> planDetailsList= new ArrayList<>();
         subscribePlans.forEach(subscribePlan -> {
           SubscriptionData.SubscriptionDetails subscriptionDetails= SubscriptionData.subscriptionData.get(SubscriptionData.SubscriptionType.valueOf(subscribePlan.getSubsCribeType()));
             ClientData.PlanDetails planDetails= ClientData.PlanDetails.builder()
                     .amount(subscribePlan.getMonth()*subscriptionDetails.getAmountPerMonth())
                     .currentUses(0)
                     .subscriptionType(SubscriptionData.SubscriptionType.valueOf(subscribePlan.getSubsCribeType()))
                     .build();
             planDetailsList.add(planDetails);
         });
        return planDetailsList;
    }
}
