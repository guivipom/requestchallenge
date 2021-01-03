package challenge.microservice.statistics.request;

import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.Optional;


public interface HourlyRequestRepository extends CrudRepository<HourlyRequest,Long> {

    Optional<HourlyRequest> findByCustomerIdAndTime(final Long customerId, final Long time);
}
