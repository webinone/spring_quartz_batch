package io.barogo.adjustment.config.kafka;

import io.barogo.adjustment.common.log.CommonLog;
import io.barogo.adjustment.config.properties.KafkaTopicProperties;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

  private final KafkaProperties kafkaProperties;
  private final KafkaTopicProperties kafkaTopicProperties;
  private final CommonLog commonLog;

  @Bean
  public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProperties.getProducer().getKeySerializer());
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaProperties.getProducer().getValueSerializer());
    props.put(ProducerConfig.RETRIES_CONFIG, kafkaProperties.getProducer().getRetries());
    props.put(ProducerConfig.ACKS_CONFIG, kafkaProperties.getProducer().getAcks());

    return props;
  }

  @Bean
  public ProducerFactory<String, Object> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<?, ?> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  // TODO : 허브, 상점, 라이더 캐쉬로그 저장을 위한 produce 설정 필요


}
