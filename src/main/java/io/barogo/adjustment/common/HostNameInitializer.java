package io.barogo.adjustment.common;


import io.barogo.adjustment.common.log.CommonLog;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HostNameInitializer {

  private static String hostName;

  private final CommonLog commonLog;

  @PostConstruct
  private void initialize() {

    commonLog.info("[HostNameInitializer/initialize]");
    try {
      this.hostName = InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException e) {
      commonLog.error(">>> UnknownHostException " + e.getMessage());
    }
  }

  public static String getHostName() {
    return hostName;
  }

}
