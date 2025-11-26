package com.plinplin.auth_service.domain.entity;

import com.plinplin.auth_service.domain.enums.RoleEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private RoleEnum role;
    private Boolean active;
}
