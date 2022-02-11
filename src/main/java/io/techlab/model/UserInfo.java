package io.techlab.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class UserInfo {

  @Id
  @SequenceGenerator(name = "user_info_seq_gen", sequenceName = "ui_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_info_seq_gen")
  private Long id;

  @Column
  private String fullName;

  @Column
  private String email;

  @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
  private Set<UserRequest> userRequest = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private Set<UserRole> userRole = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<UserRequest> getUserRequest() {
    return userRequest;
  }

  public void setUserRequest(Set<UserRequest> userRequest) {
    this.userRequest = userRequest;
  }

  public Set<UserRole> getUserRole() {
    return userRole;
  }

  public void setUserRole(Set<UserRole> userRole) {
    this.userRole = userRole;
  }

}
