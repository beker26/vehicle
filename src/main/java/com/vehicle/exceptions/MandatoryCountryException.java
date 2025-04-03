package com.vehicle.exceptions;

import java.io.Serial;

public class MandatoryCountryException extends GlobalException {
  @Serial private static final long serialVersionUID = 1L;

  private MandatoryCountryException(final Issue issue) {
    super(issue);
  }

  public static MandatoryCountryException mandatoryCountryForNotGlobalCollection(
      final String collectionName) {
    return new MandatoryCountryException(
        new Issue(IssueEnum.MANDATORY_COUNTRY_NOT_GLOBAL_COLLECTION, collectionName));
  }
}
