package io.barogo.adjustment.exception;

public class KafkaServiceException extends RuntimeException {

  public KafkaServiceException(String message) {
    super(message);
  }
}

