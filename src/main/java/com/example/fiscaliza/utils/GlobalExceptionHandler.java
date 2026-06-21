package com.example.fiscaliza.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ModelAndView handle(ObjetoNaoEncontradoException ex, HttpServletRequest request){

        FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);

        flashMap.put("message", ex.getFlashMessage());

        return new ModelAndView("redirect:"+ ex.getRedirectUrl());
    }
}
