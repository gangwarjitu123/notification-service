package com.notification.request;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeRequest extends Request {

    @NotBlank(message = "clientName can not be blank or null ")
    private String clientName;

    @NotBlank(message = "clientPassword can not be blank or null ")
    private String clientPassword;


    @NotBlank(message = "clientUserName can not be blank or null ")
    private String clientUserName;


    @NotNull
    @Valid
    private List<SubscribePlan> subscribePlan;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SubscribePlan{

        @NotBlank(message = "subsCribeType can not be blank or null ")
        private String subsCribeType;


        @NotNull(message = "month can not be blank or null ")
        private Integer month;
    }
}
