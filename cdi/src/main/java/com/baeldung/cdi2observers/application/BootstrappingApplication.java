package com.baeldung.cdi.cdi2observers.application;

import com.baeldung.cdi.cdi2observers.events.SimpleEvent;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class BootstrappingApplication {

    public static void main(String... args) {
        SeContainerInitializer containerInitializer = SeContainerInitializer.newInstance();
        try (SeContainer container = containerInitializer.initialize()) {
            container.getBeanManager().fireEvent(new SimpleEvent("Welcome to Baeldung!"));
        }
    }
}
