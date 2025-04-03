package com.vehicle.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Issue implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

  @JsonProperty private final int code;

  @JsonProperty private final String message;

  @JsonProperty private List<ErrorField> details;

  public Issue(final IssueEnum issue) {
    code = issue.getCode();
    message = issue.getFormattedMessage();
  }

  public Issue(final IssueEnum issue, final Object... args) {
    code = issue.getCode();
    message = issue.getFormattedMessage(args);
  }

  public Issue(final IssueEnum issue, final List<ErrorField> details) {
    this(issue);
    this.details = details;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public List<ErrorField> getDetails() {
    return details;
  }
}
