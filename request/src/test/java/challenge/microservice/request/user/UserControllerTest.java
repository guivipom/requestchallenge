package challenge.microservice.request.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<UserDTO> jsonRequestAttempt;

    @Autowired
    private JacksonTester<User> jsonResultAttempt;


    @Test
    void postValidRequest() throws Exception {

        // given
        UserDTO userDTO = new UserDTO("aaaaaaaa-bbbb-cccc-1111-222222222222", 1L,2L, "123.234.56.78"  );
        User expectedResponse = new User("aaaaaaaa-bbbb-cccc-1111-222222222222", 1L, 2L, "123.234.56.78");
        given(userService.userRequest(eq(userDTO))).willReturn(expectedResponse);

        // when
        MockHttpServletResponse response = mvc.perform(post("/user/create").contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestAttempt.write(userDTO).getJson()))
                .andReturn().getResponse();
        // then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(
                jsonResultAttempt.write(
                        expectedResponse
                ).getJson());
    }

    @Test
    void postRequestWithMissingFields() throws Exception {

        // given
        UserDTO userDTO = new UserDTO("aaaaaaaa-bbbb-cccc-1111-222222222222", null,2L, "123.234.56.78"  );

        // when
        MockHttpServletResponse response = mvc.perform(post("/user/create").contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestAttempt.write(userDTO).getJson()))
                .andReturn().getResponse();
        // then
        then(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}