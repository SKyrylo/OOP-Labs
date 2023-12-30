package org.fpm.di.myClasses;

import org.fpm.di.example.*;
import org.fpm.di.interfaces.Binder;
import org.fpm.di.interfaces.Configuration;

public class MyConfigurationExtended implements Configuration {
    @Override
    public void configure(Binder binder) {
        binder.bind(MySingleton.class);
        binder.bind(MyPrototype.class);
        binder.bind(MyObjectWithNoValidConstructors.class);

        binder.bind(MyObjectWithDependencyChain.class);  // Uses MyPrototype and UseA

        binder.bind(UseA.class);

        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());
    }
}
