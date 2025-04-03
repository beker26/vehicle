package com.vehicle.exceptions;

import java.io.Serial;

public class GlobalException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  private final Issue issue;

  protected GlobalException(final Issue issue) {
    super(issue.getMessage());
    this.issue = issue;
  }

  public Issue getIssue() {
    return issue;
  }
}
