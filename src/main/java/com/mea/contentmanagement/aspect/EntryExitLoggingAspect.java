/**
 * Copyright TCS. All Rights Reserved.
 * This software is the proprietary to TCS.
 * Use is subject to license terms.
 */
package com.mea.contentmanagement.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * The Class EntryExitLoggingAspect.
 *
 * @author TCS
 */
@Aspect
@Component
public class EntryExitLoggingAspect {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(EntryExitLoggingAspect.class);

	/**
	 * Log before.
	 *
	 * @param joinPoint
	 *            the join point
	 */
	@Before("execution(* com.mea.contentmanagement.*.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {

		LOGGER.info("ENTRY {}", joinPoint.getSignature());

	}

	/**
	 * Log after.
	 *
	 * @param joinPoint
	 *            the join point
	 */
	@After("within(com.mea.contentmanagement.*.*) && execution(* com.mea.contentmanagement.*.*.*(..))")
	public void logAfter(JoinPoint joinPoint) {

		LOGGER.info("EXIT {}", joinPoint.getSignature());

	}
}