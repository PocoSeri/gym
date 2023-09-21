package com.gym.gym.base.service;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

@Component
public class CollectionIgnoreBeanUtils extends BeanUtilsBean {
    public CollectionIgnoreBeanUtils() {
    }

    public void copyProperty(Object dest, String name, Object value) throws InvocationTargetException, IllegalAccessException {
        if (!(value instanceof Collection)) {
            if (value == null) {
                try {
                    Object currentValue = PropertyUtils.getProperty(dest, name);
                    if (currentValue instanceof Collection) {
                        return;
                    }
                } catch (NoSuchMethodException var5) {
                }
            }

            super.copyProperty(dest, name, value);
        }
    }
}
