package com.gym.gym.base.service;

import com.gym.gym.exception.InvalidPatchDtoException;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

@Component
public class NullAndListAwareBeanUtilsBean extends BeanUtilsBean {
    public NullAndListAwareBeanUtilsBean() {
    }

    public void copyProperty(Object dest, String name, Object value) throws IllegalAccessException, InvocationTargetException {
        if (value != null) {
            if (value instanceof Collection) {
                throw new InvalidPatchDtoException(name);
            } else {
                super.copyProperty(dest, name, value);
            }
        }
    }
}
