package com.core.authserver.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.*;

import com.core.authserver.constants.ModConstant;
import com.core.authserver.validations.GlobalValidation;
 

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler 
{
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    HashMap<String,String> details = new HashMap<String,String>();
    details.put("server_error",ex.getLocalizedMessage());
    GlobalValidation response = new GlobalValidation("server_error", details, ModConstant.StatusCode.SUCCESS);
    return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
 
 
//   @ExceptionHandler(RecordNotFoundException.class)
//   public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
//     HashMap<String,String> details = new HashMap<String,String>();
//     details.put("Record Not Found",ex.getLocalizedMessage());
//     GlobalValidation response = new GlobalValidation("validations", details, ModConstant.StatusCode.SUCCESS);
//     return new ResponseEntity(response, HttpStatus.NOT_FOUND);
//   }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    HashMap<String,String> details = new HashMap<String,String>();
    for(ObjectError error : ex.getBindingResult().getAllErrors()) {
      details.put(error.getCode(),error.getDefaultMessage());
    }
    GlobalValidation response = new GlobalValidation("validations", details, ModConstant.StatusCode.SUCCESS);
    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
  }
}