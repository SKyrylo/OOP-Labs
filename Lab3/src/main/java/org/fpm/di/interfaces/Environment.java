package org.fpm.di.interfaces;

import org.fpm.di.interfaces.Configuration;
import org.fpm.di.interfaces.Container;

public interface Environment {
    Container configure(Configuration configuration);
}
