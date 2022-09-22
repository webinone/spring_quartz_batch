package io.barogo.adjustment.exception;

public class KafkaRetryException extends RuntimeException {

  public KafkaRetryException() {
    super();
  }

  public KafkaRetryException(String message) {
    super(message);
  }
}
