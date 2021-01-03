package challenge.microservice.statistics.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RequestServiceImpl implements RequestService {

    private final HourlyRequestRepository requestRepository;

    @Override
    public List<HourlyRequest> getHourlyStatistics(Long customerId, Timestamp Day) {
        return null;
    }

    @Override
    public HourlyRequest postValidRequest(Long customerId, Long time) {
        HourlyRequest hourlyRequest = requestRepository.findByCustomerIdAndTime(customerId,time)
                .orElseGet(() -> {
                    log.info("Creating new row for customer {}",
                            customerId);
                    return requestRepository.save(
                            new HourlyRequest(customerId,0l,0l, time));
                });
        hourlyRequest.setRequestCount(hourlyRequest.getRequestCount()+1);
        return requestRepository.save(hourlyRequest);
    }

    @Override
    public HourlyRequest postInvalidRequest(Long customerId, Long time) {
        HourlyRequest hourlyRequest = requestRepository.findByCustomerIdAndTime(customerId,time)
                .orElseGet(() -> {
                    log.info("Creating new row for customer {}",
                            customerId);
                    return requestRepository.save(
                            new HourlyRequest(customerId,0l,0l, time));
                });
        hourlyRequest.setRequestCount(hourlyRequest.getRequestCount()+1);
        hourlyRequest.setInvalidCount(hourlyRequest.getInvalidCount()+1);

        return requestRepository.save(hourlyRequest);
    }
}
