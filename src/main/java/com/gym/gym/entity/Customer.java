package com.gym.gym.entity;

import com.gym.gym.base.model.BaseModel;
import com.gym.gym.enums.Roles;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


@Component
@Document(collection = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseModel<String> {
    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private Integer age;
    private Boolean isActive = true;
    private Roles role;

}
