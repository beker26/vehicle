package com.vehicle.exceptions;

import java.io.Serial;


public class BadRequestException extends GlobalException {

  @Serial private static final long serialVersionUID = 1L;

  private BadRequestException(final Issue issue) {
    super(issue);
  }

  public static BadRequestException theNumberOfTheYearIsDifferentFromFour() {
    return new BadRequestException(new Issue(IssueEnum.THE_NUMBER_OF_THE_YEAR_IS_DIFFERENT_FROM_FOUR));
  }

}
