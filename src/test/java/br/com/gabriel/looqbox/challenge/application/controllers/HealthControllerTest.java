package br.com.gabriel.looqbox.challenge.application.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("integration")
@DisplayName("Test /health endpoint")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HealthControllerTest {

  private static final String HEALTH_URL = "/health";
  private static final String EXPECTED_STATUS = "UP";
  private static final String EXPECTED_SERVICE = "pokemon-service";
  @Autowired
  private WebApplicationContext webApplicationContext;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  @DisplayName("Should return health data correctly")
  void test1() throws Exception {
    this.mockMvc.perform(get(HEALTH_URL))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.service").value(EXPECTED_SERVICE))
      .andExpect(jsonPath("$.status").value(EXPECTED_STATUS))
      .andReturn();
  }


}
