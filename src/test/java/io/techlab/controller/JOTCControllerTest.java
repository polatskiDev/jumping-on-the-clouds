package io.techlab.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.techlab.component.MessageComponent;
import io.techlab.data.request.JotcRequest;
import io.techlab.service.JOTCService;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test for controller class
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class JOTCControllerTest {

  private ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private MockMvc mockMvc;

  public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

  private JOTCController jotcController;
  private JOTCService jotcService;
  private MessageComponent messageComponent;



  public JOTCControllerTest(){
    jotcService = Mockito.mock(JOTCService.class);
    messageComponent =Mockito.mock(MessageComponent.class);
    jotcController = new JOTCController(jotcService,messageComponent);
  }

  @Before
  public void setUp(){
    this.mockMvc = MockMvcBuilders.standaloneSetup(jotcController).build();
  }

  @Test
  public void testControllerForSolveJotcProblem() throws Exception{
    JotcRequest request = new JotcRequest();
    request.setEmail("orhanpolat@gmail.com");
    List<Integer> cloudList = new ArrayList<>();
    cloudList.add(0);
    cloudList.add(1);
    cloudList.add(0);
    cloudList.add(0);
    cloudList.add(1);
    cloudList.add(0);
    request.setJotcArray(cloudList);
    String json = mapper.writeValueAsString(request);
    this.mockMvc.perform(MockMvcRequestBuilders.post(
        "/jotc/solution").
        contentType(APPLICATION_JSON_UTF8).
        content(convertObjectToJsonBytes(request))).
        andExpect(status().isOk());
  }

  @Test
  public void testControllerForGetRequest() throws Exception{
    Long userId = Long.valueOf(1);
    this.mockMvc.perform(MockMvcRequestBuilders.get(
        "/jotc/totalAllRequest?loginUserId=" + userId).
        contentType(MediaType.APPLICATION_JSON).
        characterEncoding("utf-8")).
        andExpect(status().isOk());
  }

  @Test
  public void testControllerForGetTotalRequestPerUser() throws Exception{
    Long userId = Long.valueOf(1);
    this.mockMvc.perform(MockMvcRequestBuilders.get(
        "/jotc/totalUserRequest?loginUserId=" + userId).
        contentType(MediaType.APPLICATION_JSON).
        characterEncoding("utf-8")).
        andExpect(status().isOk());
  }

  @Test
  public void testControllerForRemoveUserProcess() throws Exception{
    Long userId = Long.valueOf(1);
    this.mockMvc.perform(MockMvcRequestBuilders.post(
        "/jotc/removeUserProcesses?userId=" + userId).
        contentType(MediaType.APPLICATION_JSON).
        characterEncoding("utf-8")).
        andExpect(status().isOk());
  }

  private byte[] convertObjectToJsonBytes(Object o) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    return mapper.writeValueAsBytes(o);
  }
}
