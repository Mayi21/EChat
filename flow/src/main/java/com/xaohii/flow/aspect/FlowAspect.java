package com.xaohii.flow.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class FlowAspect {

	private Map<String, Integer> map = new HashMap<>();

	@Pointcut(value = "execution(* com.xaohii.flow.controller..*(..))")
	public void pointcut(){}

	@Before(value = "pointcut()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String username = request.getHeader("username");
		Integer count = map.getOrDefault(username, 0);
		count += 1;
		map.put(username, count);
		System.out.println(map);;
	}

}
