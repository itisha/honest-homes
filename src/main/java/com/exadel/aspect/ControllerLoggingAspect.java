package com.exadel.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerLoggingAspect.class);

    @Before("execution(* com.exadel.controller..*.*(..)))")
    public void logBefore(JoinPoint joinPoint) {
        String argScv = "";
        Object[] signatureArgs = joinPoint.getArgs();
        if (signatureArgs != null && signatureArgs.length > 0) {
            StringBuilder args = new StringBuilder();
            for (Object signatureArg : signatureArgs) {
                args.append(signatureArg.toString()).append(",");
            }
            argScv = args.substring(0, args.length() - 1);
        }
        LOGGER.info("REST CALL >> " + joinPoint.getSignature().getDeclaringTypeName() +
                "." + joinPoint.getSignature().getName() + "(" + argScv + ")");
    }
}