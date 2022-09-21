package io.barogo.adjustment.common.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommonLog {

  private final ObjectMapper objectMapper;

  private Map<String, Object> toCommonLogMessageHashMap(CommonLogLevel commonLogLevel) {
    Map<String, Object> message = new HashMap<>();
    message.put("level", commonLogLevel.getLevel());
    message.put("time", Instant.now().toEpochMilli());
    message.put("v", 1);

    return message;
  }

  private String toCommonLogString(CommonLogLevel commonLogLevel, Map<String, Object> zoo) {
    Map<String, Object> message = toCommonLogMessageHashMap(commonLogLevel);
    message.put("zoo", zoo);

    try {
      return objectMapper.writeValueAsString(message);
    } catch (JsonProcessingException e) {
      return null;
    }
  }

  private String toCommonLogString(CommonLogLevel commonLogLevel, String logMessage) {
    Map<String, Object> message = toCommonLogMessageHashMap(commonLogLevel);
    Map<String, Object> zoo = new HashMap<>();
    zoo.put("message", logMessage);
    message.put("zoo", zoo);

    try {
      return objectMapper.writeValueAsString(message);
    } catch (JsonProcessingException e) {
      return null;
    }
  }

  public void trace(Map<String, Object> zoo) {
    log.trace(toCommonLogString(CommonLogLevel.TRACE, zoo));
  }

  public void trace(String logMessage) {
    log.trace(toCommonLogString(CommonLogLevel.TRACE, logMessage));
  }

  public void debug(Map<String, Object> zoo) {
    log.debug(toCommonLogString(CommonLogLevel.DEBUG, zoo));
  }

  public void debug(String logMessage) {
    log.debug(toCommonLogString(CommonLogLevel.DEBUG, logMessage));
  }

  public void info(Map<String, Object> zoo) {
    log.info(toCommonLogString(CommonLogLevel.INFO, zoo));
  }

  public void info(String logMessage) {
    log.info(toCommonLogString(CommonLogLevel.INFO, logMessage));
  }

  public void warn(Map<String, Object> zoo) {
    log.warn(toCommonLogString(CommonLogLevel.WARN, zoo));
  }

  public void warn(String logMessage) {
    log.warn(toCommonLogString(CommonLogLevel.WARN, logMessage));
  }

  public void error(Map<String, Object> zoo) {
    log.error(toCommonLogString(CommonLogLevel.ERROR, zoo));
  }

  public void error(String logMessage) {
    log.error(toCommonLogString(CommonLogLevel.ERROR, logMessage));
  }
}
