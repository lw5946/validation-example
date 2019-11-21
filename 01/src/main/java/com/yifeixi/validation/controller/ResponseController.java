package com.yifeixi.validation.controller;

import cn.hutool.core.collection.CollUtil;
import com.yifeixi.validation.entity.User;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 返回参数校验
 *
 * @author YiFeiXi
 */
@RestController
public class ResponseController {
  static final String RESPONSE_BODY_MAPPING_PATH = "/res/body";

  /** 在Spring体系中，返回值不会校验 */
  @Size(min = 2)
  @GetMapping(RESPONSE_BODY_MAPPING_PATH)
  public List<@NotNull User> response() {
    return CollUtil.newArrayList();
  }
}
