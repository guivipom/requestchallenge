package challenge.microservice.request.user;

import java.util.Optional;

public interface UserService {

    User userRequest(UserDTO userDTO);

    boolean isBlacklisted(String ip);

    boolean isCustomerBanned(long customerId);
}
