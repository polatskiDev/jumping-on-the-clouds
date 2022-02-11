package io.techlab;

import io.techlab.model.UserInfo;
import io.techlab.model.UserRequest;
import io.techlab.model.UserRole;
import io.techlab.repository.UserInfoRepository;
import io.techlab.repository.UserRequestRepository;
import io.techlab.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class AssessmentApplication {

	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	UserRoleRepository userRoleRepository;

	public static void main(String[] args) {
		SpringApplication.run(AssessmentApplication.class, args);
	}

	/**
	 * below methods are written to insert an admin user and its role by using hibernate. Sequence usage is triggered.
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void initDataInsertAfterStartUp(){
		UserInfo userInfo = insertDataInfo();
		insertUserRole(userInfo);
	}

	private UserInfo insertDataInfo() {

		UserInfo newUser = new UserInfo();
		newUser.setFullName("Daniel Deege");
		newUser.setEmail("d.deege@tech-lab.io");
		userInfoRepository.save(newUser);
		return newUser;
	}

	private void insertUserRole(UserInfo userInfo) {
		UserRole userRole = new UserRole();
		userRole.setRoleCode("admin");
		userRole.setUser(userInfo);
		userRoleRepository.save(userRole);
	}

}
