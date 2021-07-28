package com.artemsilantev.web.handler;

import com.artemsilantev.core.exception.IllegalEntityException;
import com.artemsilantev.core.exception.NoRecordException;
import com.artemsilantev.web.exception.ApiError;
import com.artemsilantev.web.exception.IllegalResourceException;
import com.artemsilantev.web.exception.InternalServerException;
import com.artemsilantev.web.exception.NotValidArgumentsException;
import com.artemsilantev.web.exception.ResourceNotFoundException;
import java.util.ArrayList;
import javax.validation.ConstraintViolationException;
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
  protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    var apiError = new ApiError(status.value(), exception.getMessage());
    return super.handleExceptionInternal(exception, apiError, headers, status, request);
  }

  @ExceptionHandler(value = {NoRecordException.class})
  public ResponseEntity<Object> handleNoRecordException(
      NoRecordException exception, WebRequest request) {
    var notFoundException = new ResourceNotFoundException(exception.getMessage());
    return handleExceptionInternal(notFoundException, null,
        new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = {IllegalEntityException.class})
  public ResponseEntity<Object> handleIllegalEntityException(
      IllegalEntityException exception, WebRequest request) {
    var illegalResourceException = new IllegalResourceException(exception.getMessage());
    return handleExceptionInternal(illegalResourceException, null,
        new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(value = {ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolationException(
      ConstraintViolationException exception, WebRequest request) {
    var errors = new ArrayList<String>();
    exception.getConstraintViolations().forEach(error
        -> errors.add(error.getPropertyPath().toString() + " " + error.getMessage()));
    var notValidArgumentException = new NotValidArgumentsException(errors, "Arguments not valid. ");
    return handleExceptionInternal(notValidArgumentException, null, new HttpHeaders(),
        HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException exception,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    var errors = new ArrayList<String>();
    exception.getBindingResult().getFieldErrors().forEach(error ->
        errors.add(error.getField() + ": " + error.getDefaultMessage()));
    exception.getBindingResult().getGlobalErrors().forEach(error ->
        errors.add(error.getObjectName() + ": " + error.getDefaultMessage()));
    var notValidArgumentException = new NotValidArgumentsException(errors, "Arguments not valid. ");
    return handleExceptionInternal(notValidArgumentException, null, headers, status, request);
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Object> handleInternalServerException(Exception exception,
      WebRequest request) {
    log.error("Error occurred: {} - {}", exception.getClass(), exception.getMessage());
    var internalServerException = new InternalServerException(
        "Problem on server side. Please try again later.");
    return handleExceptionInternal(internalServerException, null,
        new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }
}
