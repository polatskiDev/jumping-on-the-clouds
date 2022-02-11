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

/**
 * This table is used to store user roles for authorization
 *
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class UserRole {

  @Id
  @SequenceGenerator(name = "user_role_seq_gen", sequenceName = "us_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_role_seq_gen")
  private Long id;

  @ManyToOne
  @JoinColumn(name="user_id",referencedColumnName="id", nullable = false)
  private UserInfo user;

  @Column
  private String roleCode;

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

  public String getRoleCode() {
    return roleCode;
  }

  public void setRoleCode(String roleCode) {
    this.roleCode = roleCode;
  }
}
