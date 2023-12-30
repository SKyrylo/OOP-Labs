package org.fpm.di.classes;

import org.fpm.di.exceptions.ClassNotBoundException;
import org.fpm.di.exceptions.NoValidConstructorException;
import org.fpm.di.interfaces.Binder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BinderImpl implements Binder {
    private final Map<Class<?>, Class<?>> dependencyMap;
    private final Map<Class<?>, Object> singletonMap;

    public BinderImpl() {
        dependencyMap = new HashMap<>();
        singletonMap = new HashMap<>();
    }

    @Override
    public <T> void bind(Class<T> clazz) {
        dependencyMap.put(clazz, null);
    }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        dependencyMap.put(clazz, implementation);
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        dependencyMap.put(clazz, null);
        singletonMap.put(clazz, instance);
    }


    public <T> T getInstance(Class<T> clazz) {
        if (!dependencyMap.containsKey(clazz)) {
            throw new ClassNotBoundException(
                    "Can't get instance of \"%s\" class because it has not been bound".formatted(clazz));
        }

        Class<?> clazzNext = dependencyMap.get(clazz);
        if (clazzNext != null) {
            return (T) getInstance(clazzNext);
        }

        return isSingleton(clazz) ? getSingletonInstance(clazz) : getNewInstance(clazz);
    }

    private <T> T getSingletonInstance(Class<T> clazz) {
        if (!singletonMap.containsKey(clazz)) {
            singletonMap.put(clazz, getNewInstance(clazz));
        }

        return (T) singletonMap.get(clazz);
    }

    private <T> T getNewInstance(Class<T> clazz) {
        try {
            Constructor<T>[] constructors = (Constructor<T>[]) clazz.getConstructors();

            for (var constructor : constructors) {
                boolean isDefault = constructor.getParameterCount() == 0;
                boolean isInjectable = constructor.isAnnotationPresent(Inject.class);

                if (!isDefault && !isInjectable) continue;

                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Object[] injected = new Object[parameterTypes.length];
                Arrays.setAll(injected, i -> getInstance(parameterTypes[i]));

                return constructor.newInstance(injected);
            }

            throw new NoValidConstructorException(
                    ("Class \"%s\" has no constructors (must either have" +
                            "an @Inject attribute or require no parameters").formatted(clazz));
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isSingleton(Class<?> clazz) {
        return clazz.isAnnotationPresent(Singleton.class) || singletonMap.containsKey(clazz);
    }
}
