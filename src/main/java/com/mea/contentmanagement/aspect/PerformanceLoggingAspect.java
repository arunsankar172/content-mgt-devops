/**
 * Copyright TCS. All Rights Reserved.
 * This software is the proprietary to TCS.
 * Use is subject to license terms.
 */
package com.mea.contentmanagement.aspect;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * The Class PerformanceLoggingAspect.
 *
 * @author TCS
 */
@Aspect
@Component
public class PerformanceLoggingAspect {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(PerformanceLoggingAspect.class);

	/**
	 * Log around.
	 *
	 * @param joinPoint
	 *            the join point
	 * @return the object
	 * @throws Throwable
	 *             the throwable
	 */
	@Around("within(com.mea.referencedatamanagement.controller.*) && execution(* com.mea.referencedatamanagement.*.*.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		/** Start time in nano seconds. */
		long startTimeNano = 0;
		/** Stop time in nano seconds. */
		long stopTimeNano = 0;
		Object result = null;
		try {
			startTimeNano = System.nanoTime();
			result = joinPoint.proceed();
		} finally {
			stopTimeNano = System.nanoTime();
			long elapsedTime = TimeUnit.MILLISECONDS.convert(stopTimeNano - startTimeNano, TimeUnit.NANOSECONDS);
			LOGGER.debug("{} Total elapse time in ms: {}", joinPoint.getSignature(), elapsedTime);
		}
		return result;
	}

}