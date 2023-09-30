package com.gym.gym.entity;

import com.gym.gym.base.model.BaseModel;
import com.gym.gym.enums.Roles;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "user")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @Id
    private String id;
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Roles role;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
