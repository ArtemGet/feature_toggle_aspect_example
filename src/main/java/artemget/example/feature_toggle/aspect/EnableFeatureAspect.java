package artemget.example.feature_toggle.aspect;

import artemget.example.feature_toggle.feature.FeatureType;
import artemget.example.feature_toggle.feature.EnabledFeatures;
import artemget.example.feature_toggle.exception.FeatureDisabledException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Enable feature aspect processor for EnableFeature
 */
@Component
@Aspect
public class EnableFeatureAspect {
    private static final Class<EnableFeature> ASPECT_ANNOTATION = EnableFeature.class;
    private final EnabledFeatures enabledFeatures;

    public EnableFeatureAspect(EnabledFeatures enabledFeatures) {
        this.enabledFeatures = enabledFeatures;
    }

    /**
     * Class layer pointcut
     */
    @Pointcut("execution(* (@artemget.example.feature_toggle.aspect.EnableFeature *).*(..))")
    public void classLayerPointcut() {

    }

    /**
     * Method layer pointcut
     */
    @Pointcut("@annotation(artemget.example.feature_toggle.aspect.EnableFeature)")
    public void methodLayerPointcut() {

    }

    /**
     * EnableFeature processor, operate if method or class is marked.
     * If both class and method is marked then method data overrides class data
     * @param joinPoint -
     */
    @Before("classLayerPointcut() || methodLayerPointcut()")
    public void processFeatureToggle(final JoinPoint joinPoint) {
        EnableFeature enableFeature = ejectAnnotation(joinPoint);

        if (enableFeature == null) {
            throw new FeatureDisabledException();
        }

        FeatureType value = enableFeature.value();

        if (enabledFeatures.checkDisabled(value)) {
            throw new FeatureDisabledException();
        }
    }

    private EnableFeature ejectAnnotation(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        EnableFeature annotation = method.getAnnotation(EnableFeatureAspect.ASPECT_ANNOTATION);
        if (annotation == null) {
            Class<?> declaringClass = method.getDeclaringClass();
            annotation = declaringClass.getAnnotation(EnableFeatureAspect.ASPECT_ANNOTATION);
        }

        return annotation;
    }
}
