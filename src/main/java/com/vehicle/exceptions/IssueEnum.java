package com.vehicle.exceptions;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.IllegalFormatException;
import java.util.Objects;

public enum IssueEnum {
  YEAR_OF_MANUFACTURE_IS_LESS(1, "The vehicle's year: %s of manufacture cannot be less than 2000"),
  BAD_REQUEST(2, "Malformed Request"),
  JSON_DESERIALIZE_ERROR(3, "We could not fully understand your message, please check commas, double quotation marks or appropriate values of fields according to the documentation."),
  MANDATORY_COUNTRY_NOT_GLOBAL_COLLECTION(4, "Error: 'country' is mandatory for persistence/find in database for collection: %s");

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
