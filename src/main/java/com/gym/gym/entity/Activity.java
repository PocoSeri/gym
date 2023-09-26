package com.gym.gym.entity;

import com.gym.gym.base.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Component
@Document(collection = "activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity extends BaseModel<String> {
    @Id
    private String id;
    private String name;

}
