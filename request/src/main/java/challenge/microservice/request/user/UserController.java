package challenge.microservice.request.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    @PostMapping("/create")
    ResponseEntity<User> postResult(@RequestBody @Valid UserDTO userDTO){
        Optional<User> userProcessed = userService.userRequest(userDTO);


        return userProcessed.isPresent() ? ResponseEntity.ok(userProcessed.get()) :
                (ResponseEntity<User>) ResponseEntity.badRequest();
    }



}
