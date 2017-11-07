package com.hcl.nl.codegentool.logging;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodegenAppLogger {

	/**
	 * 
	 */
	private Logger logger;

	/**
	 * 
	 * @param classIns
	 */
	private CodegenAppLogger(Class<?> classIns) {
		logger = LoggerFactory.getLogger(classIns);
	}

	/**
	 * 
	 * @param classIns
	 * @return
	 */
	public static CodegenAppLogger logger(Class<?> classIns) {
		return new CodegenAppLogger(classIns);
	}

	/**
	 * 
	 * @param methodName
	 * @param params
	 */
	public void appendStartLog(String methodName, Object... params) {
		if (logger.isDebugEnabled()) {
			logger.debug(
					new StringBuilder().append("start of method ").append(methodName).append(" with params [")
							.append((params.length > 0) ? Arrays.asList(params).stream()
									.map(o -> "{" + o.toString() + "}").collect(Collectors.joining("\n")) : "")
							.append("]").toString());
		}
	}

	/**
	 * 
	 * @param methodName
	 */
	public void appendEndLog(String methodName) {
		if (logger.isDebugEnabled()) {
			logger.debug(new StringBuilder().append("end of method ").append(methodName).toString());
		}
	}

	/**
	 * 
	 * @param message
	 */
	public void appendInfo(String message) {
		if (logger.isInfoEnabled()) {
			logger.info(message);
		}
	}

	/**
	 * 
	 * @param message
	 */
	public void appendDebug(String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}

	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public void appendError(String message, Throwable throwable) {
		logger.error(message, throwable);
	}

	/**
	 * 
	 * @param message
	 */
	public void appendTrace(String message) {
		if (logger.isTraceEnabled()) {
			logger.trace(message);
		}
	}

	/**
	 * 
	 * @param message
	 * @param t
	 */
	public void appendTrace(String message, Throwable t) {
		if (logger.isTraceEnabled()) {
			logger.trace(message, t);
		}
	}

	/**
	 * 
	 * @param message
	 */
	public void appendError(String message) {
		logger.error(message);
	}

}
