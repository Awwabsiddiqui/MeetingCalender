package com.example.springrest.method;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class aspectClass {
//	@Before(value = "execution(*com.example.springrest.method.*)")
//	public void beforeAdvice(JoinPoint joinPoint, Ent ent, Ent meetingWithEnt) {
//		System.out.println("Before method:" + joinPoint.getSignature());
//		System.out.println("Creating Employee with first name - " + ent + ", second name - " + meetingWithEnt );
//	}
	
	@Before("execution(* com.example.springrest.controller.*.*(..))") 	//execution(* *.*.*())
    public void loggingBefore(JoinPoint jp) 
    { 
        System.out.println("Before advice is executed : "+jp.getSignature()+" ::::: "+Arrays.toString(jp.getArgs())); 
    } 
	@After("execution(* com.example.springrest.controller.*.home())") 	//execution(* *.*.*())
    public void loggingAfter() 
    { 
        System.out.println("After advice is executed"); 
    } 
	@Around("execution(* com.example.springrest.controller.*.saveBookMeeting(..))") 	//execution(* *.*.*())
    public void loggingAround() 
    { 
        System.out.println("Around advice is executed"); 
    } 
	
//	@Pointcut("within(* com.example.springrest.controller.*") 	//within(* com.example.springrest..*
//    public void pointCutObj() 
//    { 
//        System.out.println("Class advice is executed"); 
//    } 
//	@Before(value="pointCutObj()")
//    public void loggingClassAOP() 
//    { 
//		System.out.println("BEFORE Class advice is executed"); 
//    } 
}