package com.deliverykim.deliverykim.global.exception;

import java.util.List;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.deliverykim.deliverykim.global.exception.custom.UserHandlerException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class CustomExceptionHandler {

	@ExceptionHandler(BindException.class)
	public Object processValidationError(BindException bindException) {
		BindingResult result = bindException.getBindingResult();
		String message = ResponseCode.ILLEGAL_ARGUMENT.getMessage() + " - " + getValidationErrorString(result);
		ResponseCode responseCode = ResponseCode.ILLEGAL_ARGUMENT;
		responseCode.setMessage(message);

		return new CommonResponseEntity<>(responseCode, bindException);
	}

	@ExceptionHandler(UserHandlerException.class)
	public Object processUserHandlerException(UserHandlerException userHandlerException) {
		return new CommonResponseEntity<>(
			ResponseCode.get(userHandlerException.getResponseCode().getCode()),
			userHandlerException
		);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Object processMethodArgumentNotValidException(
		MethodArgumentNotValidException methodArgumentNotValidException) {
		BindingResult result = methodArgumentNotValidException.getBindingResult();

		String message = getValidationErrorString(result);
		ResponseCode responseCode = ResponseCode.ILLEGAL_ARGUMENT;
		responseCode.setMessage(message);

		return new CommonResponseEntity<>(responseCode, methodArgumentNotValidException);
	}

	private String getValidationErrorString(BindingResult result) {
		List<FieldError> errors = result.getFieldErrors();
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (FieldError error : errors) {
			sb.append(error.getField())
				.append(" : ")
				.append(error.getDefaultMessage())
				.append(" ");
		}
		sb.deleteCharAt(sb.length() - 1).append("]");
		return sb.toString();
	}
}
