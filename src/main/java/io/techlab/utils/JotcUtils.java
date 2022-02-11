package io.techlab.utils;

import io.techlab.component.MessageComponent;
import io.techlab.exception.JotcAssessmentException;
import io.techlab.message.ResponseEnums.RestException;
import io.techlab.message.RestResponse;
import org.springframework.http.HttpStatus;

/**
 * This class is used to compare exception types
 */
public class JotcUtils {

  private final MessageComponent messageComponent;

  public JotcUtils(MessageComponent messageComponent) {
    this.messageComponent = messageComponent;
  }

  public RestResponse<Integer> checkSolutionException(Exception e){
    if(e instanceof JotcAssessmentException) {
      final JotcAssessmentException ex = (JotcAssessmentException) e;
      if (ex.getErrorCode().equals(RestException.EMPTY_USER.name())) {
        return RestResponse.of(null, HttpStatus.NO_CONTENT,
            messageComponent.getErrorMessage("error.EMPTY_USER"));
      } else if (ex.getErrorCode().equals(RestException.EMPTY_LIST.name())) {
        return RestResponse.of(null, HttpStatus.BAD_REQUEST, messageComponent
            .getErrorMessage("error.EMPTY_LIST"));
      } else if (ex.getErrorCode().equals(RestException.FIRST_CLOUD.name())) {
        return RestResponse.of(null, HttpStatus.BAD_REQUEST, messageComponent
            .getErrorMessage("error.FIRST_CLOUD"));
      } else if (ex.getErrorCode().equals(RestException.LAST_CLOUD.name())) {
        return RestResponse.of(null, HttpStatus.BAD_REQUEST, messageComponent
            .getErrorMessage("error.LAST_CLOUD"));
      } else
        return RestResponse.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageComponent
            .getErrorMessage("error.global"));
    }
    return RestResponse.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageComponent
        .getErrorMessage("error.global"));
  }

  public RestResponse<Object> checkTotalRequestException(Exception e){
    if (e instanceof JotcAssessmentException) {
      final JotcAssessmentException ex = (JotcAssessmentException) e;
      if (ex.getErrorCode() != null) {
        if(ex.getErrorCode().equals(RestException.GLOBAL.name())){
          return RestResponse.of(null, HttpStatus.NOT_FOUND,
              messageComponent.getErrorMessage("error.NO_RESULT"));
        }else if(ex.getErrorCode().equals(RestException.UNAUTHORIZED.name())) {
          return RestResponse.of(null, HttpStatus.UNAUTHORIZED,
              messageComponent.getErrorMessage("error.unauthorized"));
        }else if(ex.getErrorCode().equals(RestException.NO_USER.name())){
          return RestResponse.of(null, HttpStatus.NOT_FOUND,
              messageComponent.getErrorMessage("error.noUser"));
        }
      }
    }
    else {
      return RestResponse.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageComponent
          .getErrorMessage("error.global"));
    }
    return RestResponse.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageComponent
        .getErrorMessage("error.global"));
  }
}
