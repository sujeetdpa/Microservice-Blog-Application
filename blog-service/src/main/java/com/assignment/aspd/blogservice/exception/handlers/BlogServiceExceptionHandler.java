package com.assignment.aspd.blogservice.exception.handlers;

import com.assignment.aspd.blogservice.exception.BlogServiceException;
import com.assignment.aspd.blogservice.exception.not.found.BlogServiceNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class BlogServiceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({BlogServiceNotFoundException.class})
    public final ResponseEntity<BlogServiceExceptionResponse> handleNotFoundException(BlogServiceNotFoundException exception){
        HttpStatus status=HttpStatus.NOT_FOUND;
        BlogServiceExceptionResponse response=new BlogServiceExceptionResponse();
        response.setError(exception.getMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(status.value());
        return new ResponseEntity<>(response,status);
    }
    @ExceptionHandler({ValidationException.class})
    public final ResponseEntity<BlogServiceExceptionResponse> handleValidationExceptions(ValidationException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        BlogServiceExceptionResponse exceptionResponse = new BlogServiceExceptionResponse();
        exceptionResponse.setError(exception.getMessage());
        exceptionResponse.setStatus(status.value());
        exceptionResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, status);
    }
    @ExceptionHandler({BlogServiceException.class})
    public ResponseEntity<BlogServiceExceptionResponse> handelLibraryException(BlogServiceException exception){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        BlogServiceExceptionResponse exceptionResponse = new BlogServiceExceptionResponse();
        exceptionResponse.setError(exception.getMessage());
        exceptionResponse.setStatus(status.value());
        exceptionResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, status);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpStatus statusResponse = HttpStatus.BAD_REQUEST;
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
        Iterator var10 = fieldErrors.iterator();

        String error;
        while (var10.hasNext()) {
            FieldError fieldError = (FieldError) var10.next();
            error = fieldError.getField() + ": " + fieldError.getDefaultMessage();
            errors.add(error);
        }

        var10 = globalErrors.iterator();

        while (var10.hasNext()) {
            ObjectError globalError = (ObjectError) var10.next();
            error = globalError.getObjectName() + ": " + globalError.getDefaultMessage();
            errors.add(error);
        }
        BlogServiceExceptionResponse exceptionResponse = new BlogServiceExceptionResponse();
        exceptionResponse.setError(errors.toString());
        exceptionResponse.setStatus(statusResponse.value());
        exceptionResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, headers, statusResponse);
    }
    @ExceptionHandler(value = {CannotCreateTransactionException.class})
    public ResponseEntity<BlogServiceExceptionResponse> cannotCreateTransactionException(CannotCreateTransactionException exception, WebRequest request) {
        HttpStatus status=null;
        if (exception.contains(ConnectException.class)) {
            status=HttpStatus.SERVICE_UNAVAILABLE;
        }else {
            status=HttpStatus.INTERNAL_SERVER_ERROR;
        }
        BlogServiceExceptionResponse exceptionResponse = new BlogServiceExceptionResponse();
        exceptionResponse.setError(exception.getMessage());
        exceptionResponse.setStatus(status.value());
        exceptionResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, status);
    }
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<BlogServiceExceptionResponse> handleException(RuntimeException exception){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        BlogServiceExceptionResponse exceptionResponse = new BlogServiceExceptionResponse();
        exceptionResponse.setError(exception.getMessage());
        exceptionResponse.setStatus(status.value());
        exceptionResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, status);
    }
}
