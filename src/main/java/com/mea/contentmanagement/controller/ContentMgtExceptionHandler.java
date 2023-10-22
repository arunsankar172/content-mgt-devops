/**
 * Copyright TCS. All Rights Reserved.
 * This software is the proprietary to TCS.
 * Use is subject to license terms.
 */
package com.mea.contentmanagement.controller;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mea.bpm.constants.FieldValidationErrorList;
import com.mea.bpm.constants.GeneralResponseCode;
import com.mea.bpm.constants.GeneralResponseDescription;
import com.mea.bpm.enums.MessageType;
import com.mea.bpm.resource.util.MessageUtils;
import com.mea.bpm.response.BaseResponse;
import com.mea.bpm.response.Status;
import com.mea.contentmanagement.constants.ContentMgtConstant;
import com.mea.contentmanagement.constants.ContentMgtThreadContext;
import com.mea.contentmanagement.exception.ContentMgtException;
import com.mea.contentmanagement.exception.ContentMgtResourceNotFoundException;



@ControllerAdvice
public class ContentMgtExceptionHandler {

	private static final Logger LOG = LogManager.getLogger(ContentMgtExceptionHandler.class);

	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<BaseResponse> handleConstraintViolationException(ConstraintViolationException e)
			{

		Status status = new Status();
		status.setSystem(ContentMgtConstant.CM_SYSTEM);
		status.setStatusCode(GeneralResponseCode.BAD_REQUEST_STATUS_CODE);
		status.setStatusDescription(GeneralResponseDescription.BAD_REQUEST_STATUS_DESC);
		FieldValidationErrorList fieldValidationErrorList = new FieldValidationErrorList(MessageType.ERROR);
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();

		for (ConstraintViolation<?> violation : violations) {

			fieldValidationErrorList.addFieldError(((PathImpl) violation.getPropertyPath()).getLeafNode().getName(),
					violation.getMessage(), violation.getMessage());

		}
		status.setSeverity(MessageType.ERROR.toString());// error
		return new ResponseEntity<>(new BaseResponse(ContentMgtThreadContext.getRequestId(),
				ContentMgtThreadContext.getSessionId(), status, null, fieldValidationErrorList), HttpStatus.BAD_REQUEST);

	}

	/**
	 * Handle validation error.
	 *
	 * @param ex
	 *            the ex
	 * @return the response entity
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<BaseResponse> handleValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> errors = result.getFieldErrors();

		Status status = new Status();
		status.setSystem(ContentMgtConstant.CM_SYSTEM);
		status.setStatusCode(GeneralResponseCode.BAD_REQUEST_STATUS_CODE);
		status.setStatusDescription(GeneralResponseDescription.BAD_REQUEST_STATUS_DESC);
		FieldValidationErrorList fieldValidationErrorList = new FieldValidationErrorList(
				com.mea.bpm.enums.MessageType.ERROR);

		for (org.springframework.validation.FieldError fieldError : errors) {
			fieldValidationErrorList.addFieldError(fieldError.getField(), fieldError.getDefaultMessage(),
					fieldError.getCode());
		}
		status.setSeverity(MessageType.ERROR.toString());// error
		return new ResponseEntity<>(new BaseResponse(ContentMgtThreadContext.getRequestId(),
				ContentMgtThreadContext.getSessionId(), status, null, fieldValidationErrorList), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle ref data validation exception.
	 *
	 * @param ex
	 *            the ex
	 * @return the response entity
	 */
//	@ExceptionHandler(RefDataValidationException.class)
//	public ResponseEntity<BaseResponse> handleRefDataValidationException(RefDataValidationException ex) {
//		Status status = new Status();
//		status.setSystem(ContentMgtConstant.CM_SYSTEM);
//		status.setStatusCode(GeneralResponseCode.VALIDATION_STATUS_CODE);
//		status.setStatusDescription(GeneralResponseDescription.VALIDATION_STATUS_DESC);
//		status.setErrorCode(ex.getMessage());
//		status.setErrorDescription(MessageUtils.getProperty(ex.getMessage()));
//		status.setSeverity(MessageType.ERROR.toString());// error
//		return new ResponseEntity<>(new BaseResponse(ContentMgtThreadContext.getRequestId(),
//				ContentMgtThreadContext.getSessionId(), status, ex.getAdditionalStatusList(), null), HttpStatus.OK);
//	}

	/**
	 * Handle ref data resource not found exception.
	 *
	 * @param ex
	 *            the ex
	 * @return the response entity
	 */
	@ExceptionHandler(ContentMgtResourceNotFoundException.class)
	public ResponseEntity<BaseResponse> handleContentMgtResourceNotFoundException(ContentMgtResourceNotFoundException ex) {
		Status status = new Status();
		LOG.info("ContentMgtResourceNotFoundException-"+ex.getMessage());
		status.setSystem(ContentMgtConstant.CM_SYSTEM);
		status.setStatusCode(GeneralResponseCode.RESOURCE_NOT_FOUND_STATUS_CODE);
		status.setStatusDescription(GeneralResponseDescription.RESOURCE_NOT_FOUND_STATUS_DESC);
		status.setErrorCode(ex.getMessage());
		status.setErrorDescription(MessageUtils.getProperty(ex.getMessage()));
		status.setSeverity(MessageType.ERROR.toString());// error

		return new ResponseEntity<>(new BaseResponse(ContentMgtThreadContext.getRequestId(),
				ContentMgtThreadContext.getSessionId(), status, ex.getAdditionalStatusList(), null), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handle ref data transaction exception.
	 *
	 * @param ex
	 *            the ex
	 * @return the response entity
	 */
//	@ExceptionHandler(RefDataTransactionException.class)
//	public ResponseEntity<BaseResponse> handleRefDataTransactionException(RefDataTransactionException ex) {
//		Status status = new Status();
//		status.setSystem(ContentMgtConstant.CM_SYSTEM);
//		status.setStatusCode(GeneralResponseCode.EXCEPTION_STATUS_CODE);
//		status.setStatusDescription(GeneralResponseDescription.EXCEPTION_STATUS_DESC);
//		status.setErrorCode(ex.getMessage());
//		status.setErrorDescription(MessageUtils.getProperty(ex.getMessage()));
//		status.setSeverity(MessageType.ERROR.toString());// error
//
//		return new ResponseEntity<>(new BaseResponse(ContentMgtThreadContext.getRequestId(),
//				ContentMgtThreadContext.getSessionId(), status, ex.getAdditionalStatusList(), null),
//				HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	/**
	 * Handle ref data exception.
	 *
	 * @param ex
	 *            the ex
	 * @return the response entity
	 */
	@ExceptionHandler(ContentMgtException.class)
	public ResponseEntity<BaseResponse> handleContentMgtException(ContentMgtException ex) {

		Status status = new Status();
		status.setSystem(ContentMgtConstant.CM_SYSTEM);
		status.setStatusCode(GeneralResponseCode.EXCEPTION_STATUS_CODE);
		status.setStatusDescription(GeneralResponseDescription.EXCEPTION_STATUS_DESC);
		status.setErrorCode(ContentMgtConstant.CM_RUN_TIME_EXCEPTION);
		status.setErrorDescription(MessageUtils.getProperty(ContentMgtConstant.CM_RUN_TIME_EXCEPTION));
		status.setSeverity(MessageType.ERROR.toString());// error

		return new ResponseEntity<>(new BaseResponse(ContentMgtThreadContext.getRequestId(),
				ContentMgtThreadContext.getSessionId(), status, null, null), HttpStatus.INTERNAL_SERVER_ERROR);
	}


}