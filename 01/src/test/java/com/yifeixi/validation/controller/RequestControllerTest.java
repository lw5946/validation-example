package com.yifeixi.validation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yifeixi.validation.entity.User;
import java.io.File;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

/** @author YiFeiXi */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestControllerTest {
  @Autowired WebApplicationContext context;
  @Autowired private ObjectMapper objectMapper;

  private MockMvc mvc;
  private DateTimeFormatter formatter;

  @Before
  public void setMvc() {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  }

  @Test
  public void modelAttrTest() throws Exception {
    User u = new User();
    u.setName("");
    u.setAge(-1);
    u.setSex(null);
    mvc.perform(
        MockMvcRequestBuilders.get(RequestController.MODEL_ATTR_MAPPING_PATH)
            .param("name", u.getName())
            .param("sex", String.valueOf(u.getSex()))
            .param("age", String.valueOf(u.getAge())));
  }

  @Test
  public void requestBodyTest() throws Exception {
    User u = new User();
    u.setName("");
    u.setAge(-1);
    u.setSex(null);
    mvc.perform(
        MockMvcRequestBuilders.post(RequestController.REQUEST_BODY_MAPPING_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(u)));
  }

  @Test
  public void requestPartAnnoTest() throws Exception {
    User u = new User();
    u.setName("");
    u.setAge(-1);
    u.setSex(null);
    File file = ResourceUtils.getFile("classpath:static/wx_s.png");
    byte[] noFile = null;
    mvc.perform(
        MockMvcRequestBuilders.multipart(RequestController.REQUEST_PART_MAPPING_PATH_ANNO)
            .file(
                new MockMultipartFile(
                    "photo",
                    "wx_s.png",
                    "multipart/form-data",
                    Files.readAllBytes(file.toPath()))));
  }

  @Test
  public void requestParamFileTest() throws Exception {
    User u = new User();
    u.setName("");
    u.setAge(-1);
    u.setSex(null);
    File file = ResourceUtils.getFile("classpath:static/wx_s.png");
    byte[] noFile = null;
    mvc.perform(
        MockMvcRequestBuilders.multipart(RequestController.REQUEST_PARAM_FILE_MAPPING_PATH)
            .file(
                new MockMultipartFile(
                    "photo", "wx_s.png", "multipart/form-data", noFile
                    /*Files.readAllBytes(file.toPath())*/ )));
  }

  @Test
  public void requestParamTest() throws Exception {
    User u = new User();
    u.setName("");
    u.setAge(-1);
    u.setSex(null);
    mvc.perform(
        MockMvcRequestBuilders.post(RequestController.REQUEST_PARAM_MAPPING_PATH)
            .param("name", "")
            .param("age", "201"));
  }

  @Test
  public void pathTest() throws Exception {
    User u = new User();
    u.setName("");
    u.setAge(-1);
    u.setSex(null);
    mvc.perform(MockMvcRequestBuilders.get(RequestController.PATH_MAPPING_PATH + "/201"));
  }
}
