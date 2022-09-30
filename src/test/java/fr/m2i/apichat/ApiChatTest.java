package fr.m2i.apichat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.m2i.apichat.controller.CanalController;

import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.repository.CanalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.Before;
import java.util.*;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations= "classpath:application-test.yml")
@AutoConfigureMockMvc
public class ApiChatTest {
	private MockMvc mockMvc;
	ObjectMapper objectMapper=new ObjectMapper();
    ObjectWriter objectWriter=objectMapper.writer();
	@Mock
	private CanalRepository canalRepository;
	@InjectMocks
	private CanalController canalController;
	Canal RECORD_1=new Canal(null,"General","desc Canal ",new Date(),new Date(),null,null);
	Canal RECORD_2=new Canal(null,"Room1","desc Room 1",new Date(),new Date(),null,null);
	Canal RECORD_3=new Canal(null,"Room2","desc Room 2",new Date(),new Date(),null,null);
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc= MockMvcBuilders.standaloneSetup(canalController).build();
	}
	@Test
	public void getAllRecords_success() throws Exception{
		List<Canal> canaux=new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2,RECORD_3));
		Mockito.when(canalRepository.findAll()).thenReturn(canaux);
		mockMvc.perform(MockMvcRequestBuilders
				.get("/v1/canaux")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)))
				.andExpect((ResultMatcher) jsonPath("$[2].name",is("Room2")));
	}







	/*@InjectMocks CanalService dao;
	@Mock ICanalService service;
	@Test
	void contextLoads() {
	}
	@Test
	@Sql(scripts="classpath:resources/insert-canaux.sql",executionPhase =Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts="classpath:resources/delete-canaux.sql",executionPhase =Sql.ExecutionPhase.AFTER_TEST_METHOD)
	void getAllCanaux(){
		Set<Canal> list=new HashSet<>();
		when(dao.getCanals()).thenReturn((List<Canal>) list);
		assertEquals(2,list.size());
		verify(dao,times(1)).getCanals();
	}*/



}
