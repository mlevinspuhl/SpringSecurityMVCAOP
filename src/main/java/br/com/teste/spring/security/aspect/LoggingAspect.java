package br.com.teste.spring.security.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
 
@Aspect
@Component
public class LoggingAspect {
	@Before("execution(* br.com.teste.spring.security.business.*.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
 
		System.out.println("logBefore() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("path : " + joinPoint.toShortString());
		System.out.println("******");
	}
	@After("execution(* br.com.teste.spring.security.business.*.*.*(..))")
	public void logAfter(JoinPoint joinPoint) {
 
		System.out.println("logAfter() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("path : " + joinPoint.toShortString());
		System.out.println("******");
	}
}