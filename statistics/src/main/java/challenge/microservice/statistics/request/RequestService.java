package challenge.microservice.statistics.request;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface RequestService {
    List<HourlyRequest> getHourlyStatistics(Long customerId, Long Day);

    HourlyRequest postValidRequest(Long customerId, Long time);

    HourlyRequest postInvalidRequest(Long customerId, Long time);




}




//    The service must also provide
//        - an endpoint to get the statistics
//        - for a specific customer
//        AND
//        - a specific day
//        The response must also contain the total number of requests for that day.