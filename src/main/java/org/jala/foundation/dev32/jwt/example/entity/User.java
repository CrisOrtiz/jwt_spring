package org.jala.foundation.dev32.jwt.example.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @NotNull
  private String name;
  @NotNull
  @Column(unique = true)
  private String userName;
  @NotNull
  private String email;
  @NotNull
  private String password;
  @NotNull
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_rol", joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "rol_id"))
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(@NotNull String name, @NotNull String userName, @NotNull String email,
      @NotNull String password) {
    this.name = name;
    this.userName = userName;
    this.email = email;
    this.password = password;
  }
}
