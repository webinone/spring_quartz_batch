package io.barogo.adjustment.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "kafka.topic")
public class KafkaTopicProperties {

  private String groupId;

  private final HubTransactions hubTransactions = new HubTransactions();
  private final DistributorTransactions distrobutorTransactions = new DistributorTransactions();
  private final StoreTransactions storeTransactions = new StoreTransactions();

  @Getter
  @Setter
  public static class HubTransactions {
    private String name;
    private String groupId;
    private Producer producer = new Producer();
  }

  @Getter
  @Setter
  public static class DistributorTransactions {
    private String name;
    private String groupId;
    private Producer producer = new Producer();
  }

  @Getter
  @Setter
  public static class StoreTransactions {
    private String name;
    private String groupId;
    private Producer producer = new Producer();
  }

  @Getter
  @Setter
  public static class Producer {
    private String acks;
  }
}
