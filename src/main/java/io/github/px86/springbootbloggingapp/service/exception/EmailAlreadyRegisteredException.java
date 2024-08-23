package io.github.px86.springbootbloggingapp.service.exception;

public class EmailAlreadyRegisteredException extends Exception {

  public EmailAlreadyRegisteredException(String errorMessage) {
    super(errorMessage);
  }
}
