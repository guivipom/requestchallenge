package challenge.microservice.request.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String userId;
    private Long customerId;
    private Long tagId;
    private String remoteIP;

    public User(final String userId,final Long customerId,final Long tagId,final String remoteIP ){
        this(null, userId, customerId,tagId,remoteIP);
    }

    public User() {

    }
}
