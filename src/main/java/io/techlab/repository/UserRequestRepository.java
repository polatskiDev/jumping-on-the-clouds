package io.techlab.repository;

import io.techlab.model.UserInfo;
import io.techlab.model.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRequestRepository extends JpaRepository<UserRequest, Long> {

  long deleteByUser(UserInfo user);

}
