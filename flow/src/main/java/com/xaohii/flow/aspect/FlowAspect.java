package com.xaohii.flow.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class FlowAspect {

	@Pointcut(value = "execution(* com.xaohii.flow.controller.*(..)")
	public void pointcut(){}

	@Before(value = "pointcut()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		System.out.println(request.getHeader("username"));
		System.out.println(request.getRequestURI());
		System.out.println(request.getMethod());
	}

}
