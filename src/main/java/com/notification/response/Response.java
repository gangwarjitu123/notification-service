package com.notification.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.notification.exception.ApiError;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private String requestId;
    private  String status;
    private Object data;
    private ApiError error;
}
