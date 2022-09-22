package io.barogo.adjustment.exception;

public class KafkaSeekSkipException extends RuntimeException {

  public KafkaSeekSkipException() {
    super();
  }

  public KafkaSeekSkipException(String message) {
    super(message);
  }
}

