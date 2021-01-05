package challenge.microservice.statistics.request;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface HourlyRequestRepository extends CrudRepository<HourlyRequest,Long> {

    Optional<HourlyRequest> findByCustomerIdAndTime(final Long customerId, final Long time);

    List<HourlyRequest> findAllByCustomerIdAndTimeBetween(final Long customerId, final Long startPeriod, final Long endPeriod);
}
