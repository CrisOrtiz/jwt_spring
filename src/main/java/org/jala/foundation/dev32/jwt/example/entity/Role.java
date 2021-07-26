package org.jala.foundation.dev32.jwt.example.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.jala.foundation.dev32.jwt.example.enums.RoleName;

@Getter
@Setter
@Entity
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @NotNull
  @Enumerated(EnumType.STRING)
  private RoleName roleName;

  public Role() {
  }

  public Role(@NotNull RoleName roleName) {
    this.roleName = roleName;
  }
}
