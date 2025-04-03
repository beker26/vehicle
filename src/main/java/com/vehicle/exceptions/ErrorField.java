package com.vehicle.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorField {

  private String code;
  private String field;
  private String defaultMessage;

  public ErrorField(String code, String field, String rejectedValue, String defaultMessage) {
    this.code = code;
    this.field = field;
    this.defaultMessage = defaultMessage;
  }
}
