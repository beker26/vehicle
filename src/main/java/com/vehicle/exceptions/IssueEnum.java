package com.vehicle.exceptions;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.IllegalFormatException;
import java.util.Objects;

public enum IssueEnum {
  BAD_REQUEST(1, "Malformed Request"),
  JSON_DESERIALIZE_ERROR(2, "We could not fully understand your message, please check commas, double quotation marks or appropriate values of fields according to the documentation."),
  VEHICLE_DOES_NOT_EXIST_IN_THE_DATA_BASE(3, "The vehicle informed does not exist in the database");

  private final Logger logger = LoggerFactory.getLogger(IssueEnum.class);
  private final int code;
  private final String message;

  IssueEnum(final int code, final String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public String getFormattedMessage(final Object... args) {
    var messageReturn = StringUtils.EMPTY;

    if (Objects.nonNull(message)) {
      try {
        messageReturn = String.format(message, args);
      } catch (final IllegalFormatException e) {
        logger.warn(ExceptionUtils.getStackTrace(e));
        messageReturn = message.replace("%s", "");
      }
    }

    return messageReturn;
  }
}
