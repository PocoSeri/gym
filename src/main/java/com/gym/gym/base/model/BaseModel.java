package com.gym.gym.base.model;

import java.io.Serializable;

public abstract class BaseModel<K extends Serializable> implements Serializable {
    public abstract K getId();

    public abstract void setId(K id);
}
