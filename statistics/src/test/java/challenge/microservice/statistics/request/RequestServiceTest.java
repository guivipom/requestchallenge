package challenge.microservice.statistics.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RequestServiceTest {

    private RequestService requestService;

    @Mock
    private HourlyRequestRepository hourlyRequestRepository;

    @BeforeEach
    void setUp() {
        requestService = new RequestServiceImpl(hourlyRequestRepository);
    }

    @Test
    public void postNewValidRequestTest(){

        given(hourlyRequestRepository.save(any())).will(returnsFirstArg());

        Long customerId= 1L;
        Long time= 1L;

        // given
        HourlyRequest hourlyRequest = requestService.postValidRequest(customerId, time);

        then(hourlyRequest.getRequestCount()).isEqualTo(1);
        then(hourlyRequest.getInvalidCount()).isEqualTo(0);
    }

    @Test
    public void postExistingValidRequestTest(){

        given(hourlyRequestRepository.save(any())).will(returnsFirstArg());

        Long customerId= 1L;
        Long time= 1L;

        HourlyRequest existingRow = new HourlyRequest(1L,1L,1L,1L);

        given(hourlyRequestRepository.findByCustomerIdAndTime(customerId,time))
                .willReturn(Optional.of(existingRow));


        // given
        HourlyRequest hourlyRequest = requestService.postValidRequest(customerId, time);

        then(hourlyRequest.getRequestCount()).isEqualTo(2);
        then(hourlyRequest.getInvalidCount()).isEqualTo(1);
    }

    @Test
    public void postNewInvalidRequestTest(){

        given(hourlyRequestRepository.save(any())).will(returnsFirstArg());

        Long customerId= 1L;
        Long time= 1L;

        // given
        HourlyRequest hourlyRequest = requestService.postInvalidRequest(customerId, time);

        then(hourlyRequest.getRequestCount()).isEqualTo(1);
        then(hourlyRequest.getInvalidCount()).isEqualTo(1);
    }

    @Test
    public void postExistingInvalidRequestTest(){

        given(hourlyRequestRepository.save(any())).will(returnsFirstArg());

        Long customerId= 1L;
        Long time= 1L;

        HourlyRequest existingRow = new HourlyRequest(1L,1L,1L,1L);

        given(hourlyRequestRepository.findByCustomerIdAndTime(customerId,time))
                .willReturn(Optional.of(existingRow));


        // given
        HourlyRequest hourlyRequest = requestService.postInvalidRequest(customerId, time);

        then(hourlyRequest.getRequestCount()).isEqualTo(2);
        then(hourlyRequest.getInvalidCount()).isEqualTo(2);
    }


}


//    @Test
//    public void checkCorrectAttemptTest(){
//
//        given(attemptRepository.save(any()))
//                .will(returnsFirstArg());
//        //given
//        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50,60, "Guillermo", 3000);
//
//        //when
//        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);
//
//        //then
//        then(resultAttempt.isCorrect()).isTrue();
//
//        verify(userRepository).save(new User("Guillermo"));
//        verify(attemptRepository).save(resultAttempt);
//        verify(gameClient).sendAttempt(resultAttempt);
//    }