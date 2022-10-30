package artemget.example.feature_toggle.aspect;

import artemget.example.feature_toggle.feature.FeatureType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * feature toggle annotation for pointing which feature is set for method or class type
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface EnableFeature {
    FeatureType value();
}
