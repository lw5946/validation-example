package com.yifeixi.validation.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/** @author YiFeiXi */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResponseBodyTest {
  @Autowired WebApplicationContext context;

  private MockMvc mvc;

  @Before
  public void setMvc() {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void responseBodyTest() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get(ResponseController.RESPONSE_BODY_MAPPING_PATH));
  }
}
