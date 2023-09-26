package com.gym.gym.base.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseModel<K extends Serializable> implements Serializable {

    @Field("registeredAt")
    private LocalDateTime registeredAt;
    @Field("modifiedAt")
    private LocalDateTime modifiedAt;
    public abstract K getId();

    public abstract void setId(K id);
}
