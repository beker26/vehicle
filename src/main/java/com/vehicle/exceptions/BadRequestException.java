package com.vehicle.exceptions;

import java.io.Serial;



public class BadRequestException extends GlobalException {

  @Serial private static final long serialVersionUID = 1L;

  private BadRequestException(final Issue issue) {
    super(issue);
  }

    public static BadRequestException yearOfManufactureIsLess(Integer year) {
      return new BadRequestException(new Issue(IssueEnum.YEAR_OF_MANUFACTURE_IS_LESS, year));
    }
}
