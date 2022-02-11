package io.techlab.service;

import io.techlab.dao.UserDao;
import io.techlab.data.request.JotcRequest;
import io.techlab.data.response.JotcProcessResponse;
import io.techlab.exception.JotcAssessmentException;
import io.techlab.message.ResponseEnums.RestException;
import io.techlab.model.UserInfo;
import io.techlab.model.UserRequest;
import io.techlab.model.UserRole;
import io.techlab.repository.UserRequestRepository;
import io.techlab.utils.Constants;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class JOTCService {

  @Autowired
  private UserRequestRepository userRequestRepository;
  @Autowired
  private UserDao userDao;

  public int solveJotcProblem(JotcRequest request){
    int result = 0;
    if(validateInput(request)){
      int count=0;
      int i=0;
      while(i<request.getJotcArray().size()-1){
        if(i+2<request.getJotcArray().size() && request.getJotcArray().get(i+2)!=1){
          count++;
          i=i+2;
        }
        else{
          count++;
          i++;
        }
      }
      result = count;
    }
    try{
      UserInfo userInfo = userDao.saveUserInfo(request);
      userDao.saveUserRequest(userInfo,request, result);
    }catch (Exception e){
      throw new JotcAssessmentException(RestException.GLOBAL.name());
    }
    return result;
  }

  private boolean validateInput(JotcRequest request) {
    if((request.getFullName() == null || request.getFullName() == "")  &&
        (request.getEmail() == null || request.getEmail() == "")){
      throw new JotcAssessmentException(RestException.EMPTY_USER.name());
    }
    int len = request.getJotcArray().size();
    if(len ==0){
      throw new JotcAssessmentException(RestException.EMPTY_LIST.name());
    }
    if(request.getJotcArray().get(0) != 0){
      throw new JotcAssessmentException(RestException.FIRST_CLOUD.name());
    }
    if(request.getJotcArray().get(len-1) != 0){
      throw new JotcAssessmentException(RestException.LAST_CLOUD.name());
    }
    return true;
  }

  // Get all requests from UserRequest table and return the count for all requests made by all users.
  public JotcProcessResponse getAllRequest(Long loginUserId){
    if(checkUserAuthentication(loginUserId)){
      List<UserRequest> userRequestList = userRequestRepository.findAll();
      JotcProcessResponse response = new JotcProcessResponse();
      response.setTotalProcess((long) userRequestList.size());
      return response;
    }else
      throw new JotcAssessmentException(RestException.UNAUTHORIZED.name());
  }

  private boolean checkUserAuthentication(Long loginUserId) {
    try{
      UserInfo user = userDao.getUserInformation(loginUserId);
      List<UserRole> roleList = new ArrayList<>(user.getUserRole());
      return roleList.stream().anyMatch(r -> Constants.ADMIN_ROLE.equals(r.getRoleCode()));
    }catch (NoResultException e){
      throw new JotcAssessmentException(RestException.NO_USER.name());
    }catch (Exception e){
      throw new JotcAssessmentException(RestException.GLOBAL.name());
    }
  }

  //Get all user list from USerInfo table and return count of the UserRequest per users.
  public List<JotcProcessResponse> getAllRequestPerUser(Long loginUserId){
    if(checkUserAuthentication(loginUserId)){
      List<UserInfo> userInfoList = userDao.getAllUsers();
      return convertDataResponse(userInfoList);
    }else
      throw new JotcAssessmentException(RestException.UNAUTHORIZED.name());
  }

  private List<JotcProcessResponse> convertDataResponse(List<UserInfo> userList) {
    List<JotcProcessResponse> processResponseList = new ArrayList<>();
    for(UserInfo userInfo: userList){
      JotcProcessResponse response = new JotcProcessResponse();
      response.setId(userInfo.getId());
      response.setEmail(userInfo.getEmail());
      response.setFullName(userInfo.getFullName());
      response.setTotalProcess((long) userInfo.getUserRequest().size());
      processResponseList.add(response);
    }
    return processResponseList;
  }

  @Transactional
  public void removeUserProcess(Long userId){
    UserInfo userInfo = userDao.getUserInformation(userId);
    userDao.removeUserRequests(userInfo);
  }
}
