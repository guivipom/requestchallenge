package challenge.microservice.request.user;

import java.util.Optional;

public interface UserService {

    Optional<User> userRequest(UserDTO userDTO);

    boolean isBlacklisted();

    boolean isCustomerAllowed();
}
