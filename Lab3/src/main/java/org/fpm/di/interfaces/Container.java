package org.fpm.di.interfaces;

public interface Container {
    <T> T getComponent(Class<T> clazz);
}
