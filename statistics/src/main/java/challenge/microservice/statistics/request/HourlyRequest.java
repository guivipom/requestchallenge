package challenge.microservice.statistics.request;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"CUSTOMER_ID", "TIME"})
)
public class HourlyRequest {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="CUSTOMER_ID")
    private Long customerId;
    private Long requestCount;
    private Long invalidCount;
    @Column(name="TIME")
    private Long time;
    private LocalDateTime date;

    public HourlyRequest(final Long customerId, final Long requestCount, final Long invalidCount, final Long time){
        this(null, customerId,requestCount,invalidCount,time,
                            LocalDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneId.systemDefault()));
    }
}