package challenge.microservice.statistics.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/statistics")
public class RequestController {

    private final RequestService requestService;

    @GetMapping("/HourlyRequest")
    ResponseEntity<List<HourlyRequest>> getHourlyStatistics(Long customerId, Timestamp day){
        return ResponseEntity.ok(requestService.getHourlyStatistics(customerId, day));

    }

    @PostMapping("/valid")
    ResponseEntity<HourlyRequest> postValidRequest(Long customerId, Long time){
        return ResponseEntity.ok(requestService.postValidRequest(customerId, time));
    }

    @PostMapping("/invalid")
    void postInvalidRequest(Long customerId, Long time){
        requestService.postValidRequest(customerId, time);
    }
}
