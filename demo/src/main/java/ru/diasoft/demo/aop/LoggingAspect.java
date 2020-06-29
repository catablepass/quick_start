package ru.diasoft.demo.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
@Aspect
public class LoggingAspect {
    private static Logger logger = LogManager.getLogger();

    private final static Set<Class<?>> errorClassSet = new HashSet<>();

    @Pointcut("within(@Loggable *))")
    public void loggableClasses() {
    }

    @Pointcut("@annotation(Loggable)")
    public void loggableMethods() {
    }

    @Before("loggableClasses() || loggableMethods()")
    public void logMethodCall(JoinPoint joinPoint) {
        String className  = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        logger.info("Method {}.{}() has been called with input parameters: {}", className, methodName, Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "loggableClasses() || loggableMethods()", returning = "result")
    public void afterDebug(final JoinPoint joinPoint, final Object result) {
        String className  = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        String value = result != null ? getPrettyJson(result) : "";
        logger.info("Method {}.{}() return value : {}", className, methodName, value);
    }

    private String getPrettyJson(Object result) {

        if (result == null)
            return null;

        if (errorClassSet.contains(result.getClass())) {
            logger.warn("The pretty json is not support for class {}", result.getClass().getCanonicalName());
            return null;
        }

        if (result.getClass().isArray()) {
            Object[] objects = (Object[]) result;
            StringBuilder sb = new StringBuilder();
            for (Object obj : objects) {
                if (obj == null) {
                    continue;
                }
                if (errorClassSet.contains(obj.getClass())) {
                    logger.warn("The pretty json is not support for class {}", result.getClass().getCanonicalName());
                    continue;
                }
                try {
                    sb.append(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj))
                            .append("\n");
                } catch (JsonProcessingException e) {
                    logWarnException(obj, e);
                }
            }
            return sb.toString();
        }

        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result);
        } catch (JsonProcessingException e) {
            logWarnException(result, e);
        }

        return null;
    }

    private void logWarnException(Object object, JsonProcessingException e){
        String message = "The pretty json is not support for class " + object.getClass().getCanonicalName() + '.' + e.getMessage();
        logger.warn(message);
        errorClassSet.add(object.getClass());
    }
}
