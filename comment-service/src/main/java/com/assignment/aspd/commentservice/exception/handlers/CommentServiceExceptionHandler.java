package com.assignment.aspd.commentservice.exception.handlers;

import com.assignment.aspd.commentservice.exception.CommentServiceException;
import com.assignment.aspd.commentservice.exception.bad.request.CommentServiceBadRequestException;
import com.assignment.aspd.commentservice.exception.not.found.CommentServiceNotFoundException;
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
public class CommentServiceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({CommentServiceNotFoundException.class})
    public final ResponseEntity<CommentServiceExceptionResponse> handleNotFoundException(CommentServiceNotFoundException exception){
        HttpStatus status=HttpStatus.NOT_FOUND;
        CommentServiceExceptionResponse response=new CommentServiceExceptionResponse();
        response.setError(exception.getMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(status.value());
        return new ResponseEntity<>(response,status);
    }
    @ExceptionHandler({CommentServiceBadRequestException.class})
    public final ResponseEntity<CommentServiceExceptionResponse> handleBadRequestFoundException(CommentServiceBadRequestException exception){
        HttpStatus status=HttpStatus.NOT_FOUND;
        CommentServiceExceptionResponse response=new CommentServiceExceptionResponse();
        response.setError(exception.getMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(status.value());
        return new ResponseEntity<>(response,status);
    }
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<CommentServiceExceptionResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CommentServiceExceptionResponse exceptionResponse = new CommentServiceExceptionResponse();
        exceptionResponse.setError(exception.getMessage());
        exceptionResponse.setStatus(status.value());
        exceptionResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, status);
    }
    @ExceptionHandler({ValidationException.class})
    public final ResponseEntity<CommentServiceExceptionResponse> handleValidationExceptions(ValidationException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CommentServiceExceptionResponse exceptionResponse = new CommentServiceExceptionResponse();
        exceptionResponse.setError(exception.getMessage());
        exceptionResponse.setStatus(status.value());
        exceptionResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, status);
    }
    @ExceptionHandler({CommentServiceException.class})
    public ResponseEntity<CommentServiceExceptionResponse> handelLibraryException(CommentServiceException exception){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        CommentServiceExceptionResponse exceptionResponse = new CommentServiceExceptionResponse();
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
        CommentServiceExceptionResponse exceptionResponse = new CommentServiceExceptionResponse();
        exceptionResponse.setError(errors.toString());
        exceptionResponse.setStatus(statusResponse.value());
        exceptionResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, headers, statusResponse);
    }
    @ExceptionHandler(value = {CannotCreateTransactionException.class})
    public ResponseEntity<CommentServiceExceptionResponse> cannotCreateTransactionException(CannotCreateTransactionException exception, WebRequest request) {
        HttpStatus status=null;
        if (exception.contains(ConnectException.class)) {
            status=HttpStatus.SERVICE_UNAVAILABLE;
        }else {
            status=HttpStatus.INTERNAL_SERVER_ERROR;
        }
        CommentServiceExceptionResponse exceptionResponse = new CommentServiceExceptionResponse();
        exceptionResponse.setError(exception.getMessage());
        exceptionResponse.setStatus(status.value());
        exceptionResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, status);
    }
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<CommentServiceExceptionResponse> handleException(RuntimeException exception){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        CommentServiceExceptionResponse exceptionResponse = new CommentServiceExceptionResponse();
        exceptionResponse.setError(exception.getMessage());
        exceptionResponse.setStatus(status.value());
        exceptionResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, status);
    }
}
