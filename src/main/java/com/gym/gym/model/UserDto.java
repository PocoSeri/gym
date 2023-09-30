package com.gym.gym.model;

import com.gym.gym.base.model.BaseDto;
import com.gym.gym.base.model.BaseIdDto;
import lombok.*;
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
