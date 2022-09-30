package fr.m2i.apichat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import fr.m2i.apichat.controller.CanalController;
import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.repository.CanalRepository;
import fr.m2i.apichat.service.IMessageService;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
@TestPropertySource(
        locations = "classpath:application-test.yml"
)
@AutoConfigureMockMvc

@WebMvcTest
public class CanalIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper=new ObjectMapper();

    @Autowired
    private CanalRepository canalRepo;



    private final Faker faker = new Faker();

    @Test
    void canCreateNewCanal() throws Exception {
        // given
        String name = String.format(
                "%s",
                faker.name()
        );

        Canal canal=new Canal(null,"General","desc Canal ",new Date(),new Date(),null,null);



        // when
         ResultActions resultActions=mockMvc
                .perform(MockMvcRequestBuilders.post("/v1/canaux/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(canal)));
        // then
        resultActions.andExpect(status().isOk());
        List<Canal> canaux = canalRepo.findAll();
        assertThat(canaux)
                .usingElementComparatorIgnoringFields("id")
                .contains(canal);
    }

    @Test
    void canDeleteCanal() throws Exception {
        // given
        Canal canal=new Canal(null,"General","desc Canal ",new Date(),new Date(),null,null);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/canaux")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(canal)))
                .andExpect(status().isOk());

        MvcResult getCanauxResult = mockMvc.perform(MockMvcRequestBuilders.get("/v1/canaux")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = getCanauxResult
                .getResponse()
                .getContentAsString();

        List<Canal> canaux = objectMapper.readValue(
                contentAsString,
                new TypeReference<>() {
                }
        );

        Long id = canaux.stream()
                .filter(s -> s.getName().equals(canal.getName()))
                .map(Canal::getId)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException(
                                "canal with name: " + canal.getName() + " not found"));

        // when
        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.delete("/v1/canaux/" + id));

        // then
        resultActions.andExpect(status().isOk());
        boolean exists = canalRepo.existsById(id);
        assertThat(exists).isFalse();
    }
}
