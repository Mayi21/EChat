package com.xaohii.flow.aspect;

import com.xaohii.flow.util.RedisUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class FlowAspect {

	private AtomicInteger times = new AtomicInteger(0);

	@Autowired
	private RedisUtil redisUtil;

	@Pointcut(value = "execution(* com.xaohii.flow.controller..*(..))")
	public void pointcut(){}

	@Before(value = "pointcut()")
	public void doBefore(JoinPoint joinPoint) {
		times.incrementAndGet();
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String username = request.getHeader("username");
		Integer integerValue = redisUtil.getIntegerValue(username);
		if (Objects.isNull(integerValue)) {
			redisUtil.put(username, 1);
		} else {
			integerValue += 1;
			redisUtil.put(username, integerValue);
			System.out.println("integer value is " + integerValue);
		}
		System.out.println(times);

	}

}
