package challenge.microservice.statistics.request;

import lombok.Value;

@Value
public class RequestDTO {
    private Long customerId;
    private Long time;
}
