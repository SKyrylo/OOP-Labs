package org.fpm.di.example;

import org.fpm.di.interfaces.Configuration;
import org.fpm.di.interfaces.Container;
import org.fpm.di.interfaces.Environment;

public class DummyEnvironment implements Environment {

    @Override
    public Container configure(Configuration configuration) {
        return new DummyContainer();
    }
}
