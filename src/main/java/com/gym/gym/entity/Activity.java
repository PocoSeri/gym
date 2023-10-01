package com.gym.gym.entity;

import com.gym.gym.base.model.BaseModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "activity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Activity extends BaseModel<String> {
    @Id
    private String id;
    private String name;

}
