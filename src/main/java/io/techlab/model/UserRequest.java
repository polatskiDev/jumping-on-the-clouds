package io.techlab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class UserRequest {

  @Id
  @SequenceGenerator(name = "user_request_seq_gen", sequenceName = "ur_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_request_seq_gen")
  private Long id;

  @ManyToOne
  @JoinColumn(name="user_id",referencedColumnName="id", nullable = false)
  private UserInfo user;

  @Column
  private String userRequest;

  @Column
  private String userResponse;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserInfo getUser() {
    return user;
  }

  public void setUser(UserInfo user) {
    this.user = user;
  }

  public String getUserRequest() {
    return userRequest;
  }

  public void setUserRequest(String userRequest) {
    this.userRequest = userRequest;
  }

  public String getUserResponse() {
    return userResponse;
  }

  public void setUserResponse(String userResponse) {
    this.userResponse = userResponse;
  }
}
