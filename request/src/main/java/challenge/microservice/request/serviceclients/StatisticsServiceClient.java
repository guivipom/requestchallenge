package challenge.microservice.request.serviceclients;

import challenge.microservice.request.user.RequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class StatisticsServiceClient {

    private final RestTemplate restTemplate;
    private final String statisticsHostUrl;

    public StatisticsServiceClient(final RestTemplateBuilder builder,
                                     @Value("${service.statistics.host}") final String statisticsHostUrl) {
        this.restTemplate = builder.build();
        this.statisticsHostUrl = statisticsHostUrl;
    }

    public void sendValidAttempt(final long customerId, final long time){
        try{
            RequestDTO dto = new RequestDTO(customerId, time);

            ResponseEntity<String> r = restTemplate.postForEntity(statisticsHostUrl +"/valid", dto, String.class);
            log.info("Statistics service response:{}", r.getStatusCode());
        }catch (Exception e){
            log.error("There is a problem sending the request.",e);
        }
    }

    public void sendInvalidAttempt(final long customerId, final long time){
        try{
            RequestDTO dto = new RequestDTO(customerId, time);

            ResponseEntity<String> r = restTemplate.postForEntity(statisticsHostUrl +"/invalid", dto, String.class);
            log.info("Statistics service response:{}", r.getStatusCode());
        }catch (Exception e){
            log.error("There is a problem sending the request.",e);
        }
    }
}
