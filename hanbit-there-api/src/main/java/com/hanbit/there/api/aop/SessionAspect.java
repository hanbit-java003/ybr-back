package com.hanbit.there.api.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hanbit.there.api.HanbitConstants;
import com.hanbit.there.api.annotation.SignInRequired;
import com.hanbit.there.api.exception.HanbitException;

@Aspect
@Component
@Order(20)
public class SessionAspect {

	private static final Logger logger = LoggerFactory.getLogger(SessionAspect.class);

	@Around("@annotation(com.hanbit.there.api.annotation.SignInRequired)")
	public Object checkSignedIn(ProceedingJoinPoint pjp) throws Throwable {
		ServletRequestAttributes reuqestAttributes
			= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		HttpSession session = reuqestAttributes.getRequest().getSession();

		MethodSignature signature = (MethodSignature) pjp.getSignature();
		SignInRequired signInRequired =
				signature.getMethod().getAnnotation(SignInRequired.class);
		String[] values = signInRequired.value();

		if (session.getAttribute(HanbitConstants.SIGNIN_KEY) == null) {
			throw new HanbitException(403, "로그인이 필요합니다.");
		}

		return pjp.proceed();
	}

}








