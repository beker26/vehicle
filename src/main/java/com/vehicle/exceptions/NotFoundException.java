package com.vehicle.exceptions;

import java.io.Serial;

public class NotFoundException extends GlobalException {

  @Serial private static final long serialVersionUID = 1L;

  private NotFoundException(final Issue issue) {
    super(issue);
  }

  public static NotFoundException noEntityFoundForPage(
      final String entityName, final Integer pageSize) {
    return new NotFoundException(new Issue(IssueEnum.MANDATORY_COUNTRY_NOT_GLOBAL_COLLECTION, entityName, pageSize));
  }
}
