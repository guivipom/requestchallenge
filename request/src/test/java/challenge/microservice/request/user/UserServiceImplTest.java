package challenge.microservice.request.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class UserServiceImplTest {

    private UserService userService;




    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(null);
    }

    void correctRequestIsprocessed(){
        //given
        UserDTO userDTO = new UserDTO("aaaaaaaa-bbbb-cccc-1111-222222222222", 1L,2L, "123.234.56.78"  );

        //when
        User userProcessed = userService.userRequest(userDTO);

        //then
        then(userProcessed).isEqualTo(userDTO);
    }

    @Test
    void blacklistedUserIsNotProcessed(){

        //given
        UserDTO userDTO = new UserDTO("aaaaaaaa-bbbb-cccc-1111-222222222222", 1L,2L, "555.555.555.555"  );

        //when
        User userProcessed = userService.userRequest(userDTO);

        // then
        then(userProcessed).isNull();
    }

    @Test
    void disabledCustomerIsNotProcessed(){

        //given
        UserDTO userDTO = new UserDTO("aaaaaaaa-bbbb-cccc-1111-222222222222", 42L,2L, "123.234.56.78"  );

        //when
        User userProcessed = userService.userRequest(userDTO);

        // then
        then(userProcessed).isNull();
    }
}