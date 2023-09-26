package com.gym.gym.model;


import com.gym.gym.base.model.BaseIdDto;
import com.gym.gym.enums.Roles;
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
public class CustomerDto extends BaseIdDto<String> {

    private String id;
    private String name;
    private String surname;
    private String email;
    private Integer age;
    private Boolean isActive;
    private Roles role;
}
