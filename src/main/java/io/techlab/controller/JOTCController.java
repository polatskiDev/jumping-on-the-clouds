package io.techlab.controller;

import io.techlab.component.MessageComponent;
import io.techlab.data.request.JotcRequest;
import io.techlab.data.response.JotcProcessResponse;
import io.techlab.exception.JotcAssessmentException;
import io.techlab.message.ResponseEnums.RestException;
import io.techlab.message.RestResponse;
import io.techlab.service.JOTCService;
import io.techlab.utils.JotcUtils;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/jotc")
public class JOTCController {

  private final JOTCService jotcService;
  private final MessageComponent messageComponent;

  /**
   * within the given request, check if the request is valid and return calculated response
   */
  @PostMapping("/solution")
  ResponseEntity<RestResponse<Integer>> solveJotcProblem(@RequestBody JotcRequest request){
    try{
      Integer result = jotcService.solveJotcProblem(request);
      return ResponseEntity.ok().body(
          RestResponse.of(result, HttpStatus.OK, messageComponent.getInfoMessage("success.global")));
    }catch(Exception e){
      return ResponseEntity.ok().body(new JotcUtils(messageComponent).checkSolutionException(e));
    }
  }

  /**
   *admin request for all processes
   */
  @GetMapping("/totalAllRequest")
  ResponseEntity<RestResponse<Object>> getRequest(@RequestParam("loginUserId") Long loginUserId){
    try{
      JotcProcessResponse responseList = jotcService.getAllRequest(loginUserId);
      return ResponseEntity.ok().body(
          RestResponse.of(responseList,HttpStatus.OK, messageComponent.getInfoMessage("success.global")));
    }catch (Exception e){
      return ResponseEntity.ok().body(new JotcUtils(messageComponent).checkTotalRequestException(e));
    }
  }

  /**
   * admin request for all processes per Users
   */
  @GetMapping("/totalUserRequest")
  ResponseEntity<RestResponse<Object>> getTotalRequestPerUser(@RequestParam("loginUserId") Long loginUserId){
    try{
      List<JotcProcessResponse> responseList =jotcService.getAllRequestPerUser(loginUserId);
      return ResponseEntity.ok().body(
          RestResponse.of(responseList,HttpStatus.OK, messageComponent.getInfoMessage("success.global")));
    }catch (Exception e){
      return ResponseEntity.ok().body(new JotcUtils(messageComponent).checkTotalRequestException(e));
    }
  }

  /**
   * Admin Request to remove user Requests
   */
  @PostMapping("/removeUserProcesses")
  ResponseEntity<RestResponse<String>> removeUserProcess(@RequestParam("userId") Long userId){
    try{
      jotcService.removeUserProcess(userId);
      return ResponseEntity.ok().body(
          RestResponse.of("",HttpStatus.OK,messageComponent.getInfoMessage("success.userDeleted")));
    }catch(Exception e){
      if (e instanceof JotcAssessmentException) {
        final JotcAssessmentException ex = (JotcAssessmentException) e;
        if (ex.getErrorCode() != null) {
          if(ex.getErrorCode().equals(RestException.GLOBAL.name())){
            return ResponseEntity.ok().body(RestResponse.of(null, HttpStatus.NO_CONTENT,
                messageComponent.getInfoMessage("error.global")));
          }else if(ex.getErrorCode().equals(RestException.NO_USER.name())) {
            return ResponseEntity.ok().body(RestResponse.of(null, HttpStatus.NOT_FOUND,
                messageComponent.getInfoMessage("error.noUser")));
          }
        }
      }
      else {
        return ResponseEntity.ok().body(RestResponse.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageComponent
            .getErrorMessage("error.global")));
      }
      return ResponseEntity.ok().body(RestResponse.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageComponent
          .getErrorMessage("error.global")));
    }
  }
}
