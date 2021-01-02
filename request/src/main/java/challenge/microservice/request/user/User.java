package challenge.microservice.request.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class User {
    private String userId;
    private Long customerId;
    private Long tagId;
    private String remoteIP;
}
