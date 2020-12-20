package com.wonsang.web.modules.account.model;

import com.sun.istack.NotNull;
import com.wonsang.web.infra.enums.AccountGrade;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter
@EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Account {
  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true) @NotNull
  private String email;

  @Column(unique = true) @NotNull
  private String nickname;

  @NotNull
  private String password;

  @NotNull
  private AccountGrade grade;

  private boolean emailVerified;

  private String emailCheckToken;

  private LocalDateTime joinedAt;

  private String bio;

  private String url;

  private String occupation;

  private String location;

  @Lob
  @Basic(fetch = FetchType.EAGER)
  private String profileImage;


}
