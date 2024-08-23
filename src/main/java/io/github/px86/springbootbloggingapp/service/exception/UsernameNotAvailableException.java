package io.github.px86.springbootbloggingapp.service.exception;

public class UsernameNotAvailableException extends Exception {

  public UsernameNotAvailableException(String errorMessage) {
    super(errorMessage);
  }
}
