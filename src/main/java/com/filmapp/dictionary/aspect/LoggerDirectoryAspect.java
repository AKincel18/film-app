package com.filmapp.dictionary.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Example of using aspect
 */

@Component
@Aspect
public class LoggerDirectoryAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggerDirectoryAspect.class);

    @Pointcut("execution(* com.filmapp.dictionary.DictionaryController.getPaginatedDirectories(..))")
    void getDirectoriesPointCut() {
    }

    @Before("getDirectoriesPointCut()")
    void beforeLogger(JoinPoint joinPoint) {
        String dictionaryTypeName = getDictionaryTypeName(joinPoint.getTarget().toString());
        logger.info("Fetching directory type: %s.".formatted(dictionaryTypeName));
    }

    @Around("@annotation(com.filmapp.dictionary.aspect.LoggerUpdateDirectory)")
    Object aroundLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String signatureMethod = proceedingJoinPoint.getSignature().getName();
        logger.info("Method %s will executed right now.".formatted(signatureMethod));
        Object proceed;
        try {
            proceed = proceedingJoinPoint.proceed();
            logger.info("Method %s ended successfully.".formatted(signatureMethod));
        } catch (Exception e) {
            logger.error("Method %s was ended with error: %s.".formatted(signatureMethod, e.getMessage()));
            throw e;
        }
        return proceed;
    }

    @After("getDirectoriesPointCut()")
    void afterLogger(JoinPoint joinPoint) {
        logger.info("Method %s ended successfully.".formatted(joinPoint.getSignature().getName()));
    }

    /**
     * Fetch directory type from name of proxy target class
     *
     * @param target target name
     * @return directory type name
     */
    private String getDictionaryTypeName(String target) {
        AtomicReference<String> dictionaryName = new AtomicReference<>();
        Arrays.stream(target.split("\\."))
                .filter(s -> s.contains("Controller"))
                .findFirst()
                .ifPresentOrElse(s -> dictionaryName.set(s.substring(0, s.indexOf("Controller"))), () -> dictionaryName.set(""));
        return dictionaryName.get();
    }
}
