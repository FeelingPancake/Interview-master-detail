package com.example.master_detail.error;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class ExceptionApiHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EntityNotExistsExeption.class})
    public ModelAndView handleValidationExceptions(Throwable ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", "Entity Not Found");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView handleOthersExceptions(Throwable ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex.toString());
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
}
