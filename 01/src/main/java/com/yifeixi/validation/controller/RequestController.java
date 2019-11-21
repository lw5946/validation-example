package com.yifeixi.validation.controller;

import com.yifeixi.validation.entity.User;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 请求参数校验
 *
 * @author YiFeiXi
 */
@RestController
public class RequestController {

  static final String MODEL_ATTR_MAPPING_PATH = "/model";
  static final String REQUEST_BODY_MAPPING_PATH = "/body";
  static final String REQUEST_PART_MAPPING_PATH_ANNO = "/part/anno";
  static final String REQUEST_PARAM_FILE_MAPPING_PATH = "/param/file";
  static final String REQUEST_PARAM_MAPPING_PATH = "/param";
  static final String PATH_MAPPING_PATH = "/path";

  /**
   * 无注解，自定义对象参数（不是简单类型），使用 {@link
   * org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor} 解析
   */
  @GetMapping(MODEL_ATTR_MAPPING_PATH)
  public User getUserByModelAttr(@Valid User user) {
    return user;
  }

  @PostMapping(REQUEST_BODY_MAPPING_PATH)
  public User getUserByBody(@RequestBody @Valid User user) {
    return user;
  }

  @PostMapping(REQUEST_PART_MAPPING_PATH_ANNO)
  public boolean getUserByRequestPartAnno(@RequestPart MultipartFile photo) {
    return true;
  }

  /** 没有注解，且参数是 Multipart 类型，与@RequestParam方式的参数一样 */
  @PostMapping(REQUEST_PARAM_FILE_MAPPING_PATH)
  public boolean getUserByRequestPart(@NotNull MultipartFile photo) {
    return true;
  }

  /**
   * "request parameters" map to query parameters, form data, and parts in multipart requests.<br>
   * "@RequestParam" 方式传参不校验参数
   */
  @PostMapping(REQUEST_PARAM_MAPPING_PATH)
  public User getUserByRequestParam(
      @RequestParam @NotEmpty String name, @RequestParam @Min(1) @Max(200) Integer age) {
    return User.builder().name(name).age(age).build();
  }

  /** "@PathVariable" 方式传参不校验参数 */
  @GetMapping(PATH_MAPPING_PATH + "/{age}")
  public User getUserByPath(@PathVariable @Min(1) @Max(200) Integer age) {
    return User.builder().age(age).build();
  }
}
