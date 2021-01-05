package challenge.microservice.statistics.request;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/statistics")
public class RequestController {

    private final RequestService requestService;

    @GetMapping("/HourlyRequest")
    ResponseEntity<List<HourlyRequest>> getHourlyStatistics(@RequestBody ObjectNode objectNode){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        ZoneId zone = ZoneId.of("UTC");

        long time = objectNode.get("date") != null ?
                LocalDate.parse(objectNode.get("date").asText(),formatter).atStartOfDay(zone).toEpochSecond() :
                objectNode.get("time").asLong();
        log.info("time", time);
        String timestring = Long.toString(time);
        return ResponseEntity.ok(requestService.getHourlyStatistics(objectNode.get("customerId").asLong(), time));

    }

    @PostMapping("/valid")
    ResponseEntity<HourlyRequest> postValidRequest(@RequestBody ObjectNode objectNode){
        return ResponseEntity.ok(requestService.postValidRequest(objectNode.get("customerId").asLong(),
                                                                objectNode.get("time").asLong()));
    }

    @PostMapping("/invalid")
    ResponseEntity<HourlyRequest> postInvalidRequest(@RequestBody ObjectNode objectNode){
        return ResponseEntity.ok(requestService.postInvalidRequest(objectNode.get("customerId").asLong(),
                objectNode.get("time").asLong()));
    }
}
