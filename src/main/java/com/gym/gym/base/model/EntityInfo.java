package com.gym.gym.base.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class EntityInfo {
    private Class<?> entityClass;
    private Class<?> entityService;

    // A map to store mappings of service classes to entity classes
    private static Map<Class<?>, Class<? extends BaseModel<?>>> serviceToEntityMap = new HashMap<>();

    public EntityInfo(Class<?> entityService) {
        this.entityService = entityService;
        this.entityClass = serviceToEntityMap.get(entityService);
    }

    // A method to add mappings to the map
    public static void addMapping(Class<?> serviceClass, Class<? extends BaseModel<?>> entityClass) {
        serviceToEntityMap.put(serviceClass, entityClass);
    }

    public static Map<Class<?>, Class<? extends BaseModel<?>>> getMapping() {
        return serviceToEntityMap;
    }
}

