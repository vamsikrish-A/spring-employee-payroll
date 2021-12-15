package com.bridgelabz.employeepayrollservice.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.message.ObjectMessage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {

    Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut(value = "execution(* com.bridgelabz.employeepayrollservice .*.*.*(..) )")
    public void myPointCut() {

    }

    @Around("myPointCut()")
    public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        Object[] array = proceedingJoinPoint.getArgs();
        log.info("method invokes "+ className + " : "+methodName + "()" + "argument : "
                +mapper.writeValueAsString(array));
        Object object = proceedingJoinPoint.proceed();
        log.info(className + " : "+ methodName + "()" + "Response : " + mapper.writeValueAsString(object));
        return object;
    }

}
