package com.yifeixi.validation.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/** @author YiFeiXi */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @NotEmpty private String name = "张三";

  @NotNull private Short sex = 0;

  @Min(1)
  @Max(200)
  private Integer age = 18;

  @NotNull private MultipartFile photo;
}
