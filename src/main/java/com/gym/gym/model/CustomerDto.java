package com.gym.gym.model;


import com.gym.gym.base.model.BaseIdDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

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
    private LocalDateTime registeredAt;
}
