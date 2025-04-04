package com.vehicle.exceptions;

import java.io.Serial;

public class NotFoundException extends GlobalException {

  @Serial private static final long serialVersionUID = 1L;

  private NotFoundException(final Issue issue) {
    super(issue);
  }

    public static NotFoundException vehicleDoesNotExistInTheDatabase() {

      return new NotFoundException(new Issue(IssueEnum.VEHICLE_DOES_NOT_EXIST_IN_THE_DATA_BASE));
    }
}
