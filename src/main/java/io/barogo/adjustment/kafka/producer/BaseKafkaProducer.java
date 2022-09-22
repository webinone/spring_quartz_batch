package io.barogo.adjustment.kafka.producer;

import io.barogo.adjustment.common.log.CommonLog;
import io.barogo.adjustment.exception.KafkaServiceException;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@RequiredArgsConstructor
public class BaseKafkaProducer<K, V> {

  private final KafkaTemplate<K, V> kafkaTemplate;
  private final CommonLog commonLog;

  /**
   * 비동기 처리
   * */
  public void sendAsyncMessage(String topic, V payload) {
    ListenableFuture<SendResult<K, V>> future = kafkaTemplate.send(topic, payload);
    future.addCallback(new ListenableFutureCallback<SendResult<K, V>>() {
      @Override
      public void onSuccess(SendResult<K, V> result) {
      }

      @Override
      public void onFailure(Throwable ex) {
        String errorMessage = "Unable to send message=[" + payload.toString() + "] due to " + ex.getMessage();
        commonLog.error(errorMessage);
        throw new KafkaServiceException(errorMessage);
      }
    });
  }

  /**
   * 동기 처리
   * */
  public SendResult<K, V> sendSyncMessage(String topic, V payload) {

    SendResult<K, V> sendResult = null;
    try{
      sendResult =  kafkaTemplate.send(topic, payload).get();
      commonLog.info("[adjustment/kafka/producer/BaseKafkaProducer/sendSyncMessage] Sent Topic=[" + sendResult.getRecordMetadata().topic() + "], Partition=[" + sendResult.getRecordMetadata().partition() + "], Message=[" + payload.toString() + "] with offset=[" + sendResult.getRecordMetadata().offset() + "]");
    } catch(ExecutionException | InterruptedException e) {
      String errorMessage = "Execution/Interrupted Exception and the details is " + e.getMessage();
      commonLog.error(errorMessage);
      throw new KafkaServiceException(errorMessage);
    } catch(Exception e) {
      String errorMessage = "Execution sending the message and the details are " + e.getMessage();
      commonLog.error(errorMessage);
      throw new KafkaServiceException(errorMessage);
    }

    return sendResult;
  }
}
