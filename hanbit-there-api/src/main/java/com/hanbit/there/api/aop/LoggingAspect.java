package com.hanbit.there.api.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void allControllerMethod() {

	}

	@Before(value="allControllerMethod()", argNames="joinPoint")
	public void logRequest(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodName = signature.toShortString();

		ServletRequestAttributes reuqestAttributes
			= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = reuqestAttributes.getRequest();
		String remoteAddr = request.getRemoteAddr();
		String uri = request.getRequestURI();

		logger.debug(methodName + " has requested by " + remoteAddr + "(" + uri + ")");
	}

}










