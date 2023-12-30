package org.fpm.di.classes;

import org.fpm.di.interfaces.Configuration;
import org.fpm.di.interfaces.Container;
import org.fpm.di.interfaces.Environment;

public class EnvironmentImpl implements Environment {

    @Override
    public Container configure(Configuration configuration) {
        BinderImpl binder = new BinderImpl();
        configuration.configure(binder);
        return new ContainerImpl(binder);
    }
}
