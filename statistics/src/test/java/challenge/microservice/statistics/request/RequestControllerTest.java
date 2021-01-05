package challenge.microservice.statistics.request;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(RequestController.class)
class RequestControllerTest {
    @MockBean
    private RequestService requestService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<RequestDTO> jsonRequest;

    @Autowired
    private JacksonTester<HourlyRequest> jsonResult;

    @Test
    public void postSuccesfulValidRequest() throws Exception {
        RequestDTO request = new RequestDTO(1l,0l);

        HourlyRequest requestResponse = new HourlyRequest(1l,1l,0l,0l);

        // when
        MockHttpServletResponse response = mvc.perform(post("/statistics/valid").contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest.write(request).getJson()))
                .andReturn().getResponse();
        // then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void postSuccessfulInvalidRequest() throws Exception {
        RequestDTO request = new RequestDTO(1l,0l);

        HourlyRequest requestResponse = new HourlyRequest(1l,1l,0l,0l);

        // when
        MockHttpServletResponse response = mvc.perform(post("/statistics/imvalid").contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest.write(request).getJson()))
                .andReturn().getResponse();
        // then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}