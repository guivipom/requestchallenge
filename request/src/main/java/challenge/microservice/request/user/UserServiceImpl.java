package challenge.microservice.request.user;

import challenge.microservice.request.serviceclients.StatisticsServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    List<String> BLACKLIST = List.of("111.111.111.111", "555.555.555");
    List<Long> BANNED_CUSTOMER = List.of(42l,288l,13l);
    private final StatisticsServiceClient statisticsClient;

    @Override
    public User userRequest(UserDTO userDTO) {
         User user = null;
        if( isBlacklisted(userDTO.getRemoteIP()) || isCustomerBanned(userDTO.getCustomerId())) {
            statisticsClient.sendInvalidAttempt(userDTO.getCustomerId(), Instant.now().getEpochSecond());
            return user;
        }
        // Process user request
        statisticsClient.sendValidAttempt(userDTO.getCustomerId(), Instant.now().getEpochSecond());

        return new User(userDTO.getUserId(), userDTO.getCustomerId(), userDTO.getTagId(), userDTO.getRemoteIP());
    }

    @Override
    public boolean isBlacklisted(String ip) {
        return BLACKLIST.contains(ip);
    }

    @Override
    public boolean isCustomerBanned(long customerId) {
        return BANNED_CUSTOMER.contains(customerId);
    }
}
