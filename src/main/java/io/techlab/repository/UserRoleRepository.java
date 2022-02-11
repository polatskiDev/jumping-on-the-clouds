package io.techlab.repository;

import io.techlab.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for User role table
 *
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
