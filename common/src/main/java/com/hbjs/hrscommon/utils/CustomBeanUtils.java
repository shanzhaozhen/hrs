package com.hbjs.hrscommon.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.lang.Nullable;
import org.springframework.util.*;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomBeanUtils extends BeanUtils {

    private static final List<String> metaList = Arrays.asList("createdDate", "createdBy", "lastModifiedDate", "lastModifiedBy");

    public static void copyPropertiesExcludeMeta(Object source, Object target, @Nullable String... ignoreProperties) {
        copyPropertiesExcludeMeta(source, target, false, ignoreProperties);
    }

    public static void copyPropertiesExcludeMeta(Object source, Object target, boolean excludeNull, @Nullable String... ignoreProperties) {
        List<String> newMetas = new ArrayList<>(metaList);
        if (ignoreProperties != null && ignoreProperties.length > 0) {
            newMetas = Arrays.asList(ignoreProperties);
        }
        if (excludeNull) {
            copyPropertiesExcludeNull(source, target, newMetas.toArray(new String[0]));
        } else {
            copyProperties(source, target, newMetas.toArray(new String[0]));
        }
    }

    public static void copyPropertiesExcludeNull(Object source, Object target, @Nullable String... ignoreProperties) {
        copyPropertiesExcludeNull(source, target, null, ignoreProperties);
    }

    public static void copyPropertiesExcludeNull(Object source, Object target, @Nullable Class<?> editable, @Nullable String... ignoreProperties) {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
                        "] not assignable to Editable class [" + editable.getName() + "]");
            }
            actualEditable = editable;
        }
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (CollectionUtils.isEmpty(ignoreList) || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null &&
                            ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);

                            if (value != null && StringUtils.hasText(value.toString())) {  //只拷贝不为null和非空串的属性
                                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                    writeMethod.setAccessible(true);
                                }
                                writeMethod.invoke(target, value);
                            }
                        }
                        catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }

    }

}
