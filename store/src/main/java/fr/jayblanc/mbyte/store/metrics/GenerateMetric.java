package fr.jayblanc.mbyte.store.metrics;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GenerateMetric {

    String key();
    Type type();

    enum Type {
        INCREMENT, DECREMENT
    }
}

