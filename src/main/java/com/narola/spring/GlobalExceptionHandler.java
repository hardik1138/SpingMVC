package com.narola.spring;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), headers, status);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ModelAndView handleResourceNotFoundExceptionV1(ResourceNotFoundException e, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("ErrorView");
        modelAndView.addObject("errMsg", "From ExceptionHandler," + request.getParameter("sortby"));
        return modelAndView;
    }

    @ExceptionHandler({EmployeeProflePicNotFound.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleResourceNotFoundException(EmployeeProflePicNotFound e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(101);
        errorResponse.setErrMsg(e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(NullPointerException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(101);
        errorResponse.setErrMsg("NullPointerException response");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
