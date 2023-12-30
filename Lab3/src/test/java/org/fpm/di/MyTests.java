package org.fpm.di;

import org.fpm.di.classes.EnvironmentImpl;
import org.fpm.di.example.*;

import org.fpm.di.exceptions.*;
import org.fpm.di.interfaces.*;
import org.fpm.di.myClasses.*;
import org.junit.Before;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

public class MyTests {

    private Container container;

    @Before
    public void setUp() {
        Environment env = new EnvironmentImpl();
        container = env.configure(new MyConfigurationExtended());
    }

    @org.junit.Test
    public void shouldInjectSingleton() {
        assertSame(container.getComponent(MySingleton.class), container.getComponent(MySingleton.class));
    }

    @org.junit.Test
    public void shouldInjectPrototype() {
        assertNotSame(container.getComponent(MyPrototype.class), container.getComponent(MyPrototype.class));
    }

    @org.junit.Test
    public void shouldBuildInjectionGraph() {
        final B bAsSingleton = container.getComponent(B.class);
        assertSame(container.getComponent(A.class), bAsSingleton);
        assertSame(container.getComponent(B.class), bAsSingleton);
    }

    @org.junit.Test
    public void shouldBuildInjectDependencies() {
        final UseA hasADependency = container.getComponent(UseA.class);
        assertSame(hasADependency.getDependency(), container.getComponent(B.class));
    }

    @org.junit.Test
    public void shouldInjectDependencyChain() {
        // Should instantiate regardless of the binding order in the configuration
        final MyObjectWithDependencyChain object = container.getComponent(MyObjectWithDependencyChain.class);

        // Should create a new instance of MyPrototype
        assertNotSame(object.getMyPrototype(), container.getComponent(MyPrototype.class));

        // Should create a new instance of UseA
        // Should inject the same instance of B into the new instance of UseA
        assertSame(object.getUseA().getDependency(), container.getComponent(B.class));
    }

    @org.junit.Test(expected = ClassNotBoundException.class)
    public void shouldThrowClassNotBoundException() {
        container.getComponent(MyObjectNotBound.class);
    }

    @org.junit.Test(expected = NoValidConstructorException.class)
    public void shouldThrowNoValidConstructorException() {
        container.getComponent(MyObjectWithNoValidConstructors.class);
    }
}
