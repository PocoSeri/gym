package com.gym.gym.entity;

import com.gym.gym.base.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "customer")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseModel<String> {
    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private int age;

}
