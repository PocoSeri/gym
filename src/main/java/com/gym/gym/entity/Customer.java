package com.gym.gym.entity;

import com.gym.gym.base.model.BaseModel;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "customer")
@Data
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

}
