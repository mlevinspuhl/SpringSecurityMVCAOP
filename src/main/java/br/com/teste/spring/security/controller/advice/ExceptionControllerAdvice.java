package br.com.teste.spring.security.controller.advice;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.spring.security.common.exception.CommonException;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionControllerAdvice {

	@ExceptionHandler(value = CommonException.class)
	public ResponseEntity<String> defaultErrorHandler(HttpServletResponse resp,
			Exception e) throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.set("HeaderKey", "HeaderDetails");
		return new ResponseEntity<String>("Rest Controller Advice Example",
				headers, HttpStatus.CONFLICT);

	}
}