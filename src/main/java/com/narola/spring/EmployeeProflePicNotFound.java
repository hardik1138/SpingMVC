package com.narola.spring;

public class EmployeeProflePicNotFound extends ResourceNotFoundException {
    public EmployeeProflePicNotFound(String message) {
        super(message);
    }
}
