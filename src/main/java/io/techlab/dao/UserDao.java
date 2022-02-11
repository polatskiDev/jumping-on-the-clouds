package io.techlab.dao;

import io.techlab.data.request.JotcRequest;
import io.techlab.exception.JotcAssessmentException;
import io.techlab.model.UserInfo;
import io.techlab.model.UserRequest;
import io.techlab.repository.UserInfoRepository;
import io.techlab.repository.UserRequestRepository;
import java.util.List;
import javax.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * This class is created for user based informations
 *
 */
@Repository
@RequiredArgsConstructor
public class UserDao {

  @Autowired
  private UserInfoRepository userInfoRepository;
  @Autowired
  private UserRequestRepository userRequestRepository;

  public UserInfo saveUserInfo(JotcRequest request) throws JotcAssessmentException{
    UserInfo user = userInfoRepository.findByFullNameOrEmail(request.getFullName(), request.getEmail());
    if(user == null){
      UserInfo newUser = new UserInfo();
      newUser.setFullName(request.getFullName());
      newUser.setEmail(request.getEmail());
      userInfoRepository.save(newUser);
      return newUser;
    }
    return user;
  }

  public List<UserInfo> getAllUsers(){
    return userInfoRepository.findAll();
  }

  public UserInfo getUserInformation(Long userId){
     return userInfoRepository.findById(userId).orElseThrow(() -> new NoResultException());
  }

  public void saveUserRequest(UserInfo userInfo, JotcRequest request, int result){
    UserRequest userRequest = new UserRequest();
    userRequest.setUserRequest(String.valueOf(request));
    userRequest.setUserResponse(String.valueOf(result));
    userRequest.setUser(userInfo);
    userRequestRepository.save(userRequest);
  }

  public void removeUserRequests(UserInfo user){
    userRequestRepository.deleteByUser(user);
  }
}
