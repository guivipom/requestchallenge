package challenge.microservice.request.user;

import lombok.Value;

@Value
public class RequestDTO {
    private Long customerId;
    private Long time;

}
