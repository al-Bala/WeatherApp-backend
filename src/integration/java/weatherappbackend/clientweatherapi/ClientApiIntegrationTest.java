package weatherappbackend.clientweatherapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherappbackend.WeatherBackendApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(classes = WeatherBackendApplication.class)
@AutoConfigureMockMvc
public class ClientApiIntegrationTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @Test
    public void testClientIntegration() throws Exception {

        ResultActions perform = mockMvc.perform(get("/week-summary/52.52/13.41")
                .content("""
                        {
                        "latitude": 50.3,
                        "longitude": 51.2
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        MvcResult mvcResult = perform.andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
    }
}
