package io.techlab.repository;

import io.techlab.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

  UserInfo findByFullNameOrEmail(String fullName, String email);
}
