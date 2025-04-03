package com.vehicle.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

  @ExceptionHandler({BadRequestException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  protected Issue processBadRequestException(final GlobalException ex, final WebRequest request) {
    return ex.getIssue();
  }

  @ExceptionHandler({NotFoundException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  protected Issue processNotFoundException(final GlobalException ex, final WebRequest request) {
    return ex.getIssue();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  protected Issue handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex, final WebRequest request) {
    final List<ErrorField> errors = new ArrayList<>();
    for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(
          new ErrorField(
              error.getCode(),
              error.getField(),
              (String) error.getRejectedValue(),
              error.getDefaultMessage()));
    }

    return new Issue(IssueEnum.BAD_REQUEST, errors);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  protected Issue handleHttpMessageNotReadableException(
      final HttpMessageNotReadableException ex, final WebRequest request) {
    return new Issue(IssueEnum.JSON_DESERIALIZE_ERROR);
  }
}
