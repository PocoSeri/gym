package com.gym.gym.base.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class BaseModel<K extends Serializable> implements Serializable {
    public abstract K getId();

    public abstract void setId(K id);

    public abstract LocalDateTime getRegisteredAt();

    public abstract void setRegisteredAt(LocalDateTime date);

    public abstract LocalDateTime getModifiedAt();

    public abstract void setModifiedAt(LocalDateTime date);
}
