package com.gym.gym.base.mapper;

import org.hibernate.collection.spi.PersistentCollection;

public class MapperUtils {
    public MapperUtils() {
    }

    public static boolean wasInitialized(Object c) {
        if (!(c instanceof PersistentCollection)) {
            return true;
        } else {
            PersistentCollection pc = (PersistentCollection)c;
            return pc.wasInitialized();
        }
    }
}
