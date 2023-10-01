package com.gym.gym.model;

import com.gym.gym.base.model.BaseIdDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseIdDto<String> {
    private String id;
    private String username;
    private String password;
    private String jwt;
}
