package challenge.microservice.request.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    @PostMapping("/create")
    ResponseEntity<String> postResult(@RequestBody @Valid UserDTO userDTO){
        User userProcessed = userService.userRequest(userDTO);


        return Objects.nonNull(userProcessed) ? ResponseEntity.ok(userProcessed.toString()) :
                ResponseEntity.badRequest().body("Your request is invalid");
    }



}
