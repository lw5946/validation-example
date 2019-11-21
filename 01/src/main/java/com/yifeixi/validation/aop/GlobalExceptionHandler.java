package com.yifeixi.validation.aop;

import java.util.ArrayList;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** @author YiFeiXi */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.OK)
  public Object bindExceptionHandler(BindException e) {
    log.error("------------------------");
    Optional.ofNullable(e.getBindingResult().getAllErrors())
        .orElse(new ArrayList<>())
        .forEach(i -> log.error("BindException: {} {}", i.getArguments(), i.getDefaultMessage()));
    log.error("------------------------");
    return true;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.OK)
  public Object methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
    log.error("------------------------");
    Optional.ofNullable(e.getBindingResult().getAllErrors())
        .orElse(new ArrayList<>())
        .forEach(i -> log.error("MethodArgumentNotValidException: {} {}", i.getArguments(), i.getDefaultMessage()));
    log.error("------------------------");
    return true;
  }
}
