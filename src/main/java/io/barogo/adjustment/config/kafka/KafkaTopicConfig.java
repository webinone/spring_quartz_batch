package io.barogo.adjustment.config.kafka;

import io.barogo.adjustment.common.HostNameInitializer;
import io.barogo.adjustment.config.properties.KafkaTopicProperties;
import io.barogo.adjustment.config.properties.KafkaTopicProperties.DistributorTransactions;
import io.barogo.adjustment.config.properties.KafkaTopicProperties.HubTransactions;
import io.barogo.adjustment.config.properties.KafkaTopicProperties.StoreTransactions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({KafkaTopicProperties.class})
public class KafkaTopicConfig {

  private final KafkaTopicProperties kafkaTopicProperties;

  @Value("${spring.profiles.active}")
  private String activeProfile;

  /**
   * 허브 캐쉬 로그 produce topic
   * */
  @Bean
  public HubTransactions hubTransactionsTopic() {
    HubTransactions hubTransactionsTopic =  kafkaTopicProperties.getHubTransactions();
    //----------------------------------------------------
    hubTransactionsTopic.setName(convertTopic(hubTransactionsTopic.getName()));
    //----------------------------------------------------
    return hubTransactionsTopic;
  }

  /**
   * 총판 캐쉬 로그 produce topic
   * */
  @Bean
  public DistributorTransactions distributorTransactionsTopic() {
    DistributorTransactions distrobutorTransactionsTopic =  kafkaTopicProperties.getDistrobutorTransactions();
    //----------------------------------------------------
    distrobutorTransactionsTopic.setName(convertTopic(distrobutorTransactionsTopic.getName()));
    //----------------------------------------------------
    return distrobutorTransactionsTopic;
  }

  /**
   * 상점 캐쉬 로그 produce topic
   * */
  @Bean
  public StoreTransactions storeTransactionsTopic() {
    StoreTransactions storeTransactionsTopic =  kafkaTopicProperties.getStoreTransactions();
    //----------------------------------------------------
    storeTransactionsTopic.setName(convertTopic(storeTransactionsTopic.getName()));
    //----------------------------------------------------
    return storeTransactionsTopic;
  }

  private String convertTopic(String topic) {
    String convertTopic = topic;
    switch (activeProfile) {
      case "develop":
        convertTopic = "dev." + topic;
        break;
      case "qc":
        convertTopic = "qc." + topic;
        break;
      case "staging":
        convertTopic = "stg." + topic;
        break;
      default:
        break;
    }

    return convertTopic;
  }

  private String getGroupIdByGreedy(Boolean greedy) {
    var groupId = kafkaTopicProperties.getGroupId();
    if (greedy) {
      groupId = groupId + "-" + HostNameInitializer.getHostName();
    }
    return groupId;
  }
}

