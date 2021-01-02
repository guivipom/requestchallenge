package challenge.microservice.request.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    @Override
    public Optional<User> userRequest(UserDTO userDTO) {
        return null;
    }

    @Override
    public boolean isBlacklisted() {
        return false;
    }

    @Override
    public boolean isCustomerAllowed() {
        return false;
    }
}
