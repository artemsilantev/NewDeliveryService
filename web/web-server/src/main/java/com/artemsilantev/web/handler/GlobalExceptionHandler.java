package com.artemsilantev.web.handler;

import com.artemsilantev.core.exception.IllegalEntityException;
import com.artemsilantev.core.exception.NoRecordException;
import com.artemsilantev.web.exceptions.ApiError;
import com.artemsilantev.web.exceptions.IllegalResourceException;
import com.artemsilantev.web.exceptions.NotValidArgumentsException;
import com.artemsilantev.web.exceptions.ResourceNotFoundException;
import java.rmi.ServerException;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    var apiError = new ApiError(status.value(), ex.getMessage());
    return super.handleExceptionInternal(ex, apiError, headers, status, request);
  }

  @ExceptionHandler(value = {NoRecordException.class})
  public ResponseEntity<Object> handleNoRecordException(NoRecordException exception,
      WebRequest request) {
    var notFoundException = new ResourceNotFoundException(exception.getMessage());
    return handleExceptionInternal(notFoundException, null,
        new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = {IllegalEntityException.class})
  public ResponseEntity<Object> handleIllegalEntityException(IllegalEntityException exception,
      WebRequest request) {
    var illegalResourceException = new IllegalResourceException(exception.getMessage());
    return handleExceptionInternal(illegalResourceException, null,
        new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    var errors = new ArrayList<String>();
    for (var error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (var error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }
    var notValidArgumentsException = new NotValidArgumentsException(errors, "Arguments not valid.");
    return handleExceptionInternal(notValidArgumentsException, null, headers, status, request);
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Object> handleServerException(Exception exception, WebRequest request) {
    log.error("Error occurred: {} - {}", exception.getClass(), exception.getMessage());
    var serverException = new ServerException("Problem on server side. Please try again later.");
    return handleExceptionInternal(serverException, null,
        new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }


}