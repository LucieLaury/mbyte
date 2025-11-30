package fr.jayblanc.mbyte.store.metrics;

import jakarta.interceptor.InterceptorBinding;

import java.lang.annotation.*;


@InterceptorBinding
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MetricsSource {

}

