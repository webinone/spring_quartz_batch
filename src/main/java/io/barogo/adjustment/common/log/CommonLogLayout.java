package io.barogo.adjustment.common.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.contrib.json.classic.JsonLayout;
import java.util.Map;

public class CommonLogLayout extends JsonLayout {

  @Override
  protected void addCustomDataToJsonMap(Map<String, Object> map, ILoggingEvent event) {
    map.remove("context");

    if (map.containsKey("level")) {
      CommonLogLevel logLevel = CommonLogLevel.findByObjectData(map.get("level"));
      map.put("level", logLevel.getLevel());
    }
  }
}
