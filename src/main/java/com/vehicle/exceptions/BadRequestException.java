package com.vehicle.exceptions;

import java.io.Serial;



public class BadRequestException extends GlobalException {

  @Serial private static final long serialVersionUID = 1L;

  private BadRequestException(final Issue issue) {
    super(issue);
  }

}
