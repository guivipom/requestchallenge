package challenge.microservice.statistics.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RequestServiceImpl implements RequestService {

    private final HourlyRequestRepository requestRepository;

    private final Long DAY_DURATION = 86399l;

    @Override
    public List<HourlyRequest> getHourlyStatistics(Long customerId, Long time) {

        long dayTime = Instant.ofEpochSecond(time).truncatedTo(ChronoUnit.DAYS).getEpochSecond();

        return requestRepository.findAllByCustomerIdAndTimeBetween( customerId, dayTime,
                                                            dayTime+DAY_DURATION);
    }

    @Override
    public HourlyRequest postValidRequest(Long customerId, Long time) {

        long hourTime = Instant.ofEpochSecond(time).truncatedTo(ChronoUnit.HOURS).getEpochSecond();


        HourlyRequest hourlyRequest = requestRepository.findByCustomerIdAndTime(customerId,hourTime)
                .orElseGet(() -> {
                    log.info("Creating new row for customer {}",
                            customerId);
                    return requestRepository.save(
                            new HourlyRequest(customerId,0l,0l, hourTime));
                });
        hourlyRequest.setRequestCount(hourlyRequest.getRequestCount()+1);
        return requestRepository.save(hourlyRequest);
    }

    @Override
    public HourlyRequest postInvalidRequest(Long customerId, Long time) {

        long hourTime = Instant.ofEpochSecond(time).truncatedTo(ChronoUnit.HOURS).getEpochSecond();


        HourlyRequest hourlyRequest = requestRepository.findByCustomerIdAndTime(customerId,hourTime)
                .orElseGet(() -> {
                    log.info("Creating new row for customer {}",
                            customerId);
                    return requestRepository.save(
                            new HourlyRequest(customerId,0l,0l, hourTime));
                });
        hourlyRequest.setRequestCount(hourlyRequest.getRequestCount()+1);
        hourlyRequest.setInvalidCount(hourlyRequest.getInvalidCount()+1);

        return requestRepository.save(hourlyRequest);
    }

}
