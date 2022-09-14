package com.example.spring_quartz_batch.common.log;

import com.example.spring_quartz_batch.common.EnumType;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum CommonLogLevel implements EnumType {
  
  TRACE(10, "추적 메시지"),
  DEBUG(20, "디버깅 메시지"),
  INFO(30, "정보성 메시지"),
  WARN(40, "경고성 메시지"),
  ERROR(50, "에러 메시지"),
  FATAL(60, "심각한 에러 메시지");

  private final Integer level;
  private final String message;

  CommonLogLevel(Integer level, String message) {
    this.level = level;
    this.message = message;
  }

  @Override
  public String getId() {
    return name();
  }

  @Override
  public String getText() {
    return getMessage();
  }

  public static CommonLogLevel findByObjectData(Object logLevelObject) {
    return Arrays.stream(CommonLogLevel.values())
        .filter(logLevel -> logLevel.name().equals(logLevelObject))
        .findAny()
        .orElse(CommonLogLevel.INFO);
  }
}
