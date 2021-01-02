package challenge.microservice.request.user;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class UserDTO {
    @NotBlank
    private String userId;
    @NotNull
    private Long customerId;
    @NotNull
    private Long tagId;
    @NotBlank
    private String remoteIP;
}
