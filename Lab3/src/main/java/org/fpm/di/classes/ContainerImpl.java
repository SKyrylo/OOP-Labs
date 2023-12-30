package org.fpm.di.classes;

import org.fpm.di.interfaces.Container;

public class ContainerImpl implements Container {
    BinderImpl binder;

    public ContainerImpl(BinderImpl binder) {
        this.binder = binder;
    }

    @Override
    public <T> T getComponent(Class<T> clazz) {
        return binder.getInstance(clazz);
    }
}
