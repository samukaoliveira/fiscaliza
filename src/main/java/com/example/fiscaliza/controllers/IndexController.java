package com.example.fiscaliza.controllers;

import com.example.fiscaliza.services.AtividadeService;
import com.example.fiscaliza.services.FiscalizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

@Controller
public class IndexController {

    @Autowired
    AtividadeService atividadeService;

    @Autowired
    FiscalizacaoService fiscalizacaoService;

    public final String ROOT_TEMPLATE = "/index";

    public ModelAndView index(){

        LocalDate dataAtual = LocalDate.now();
        ModelAndView mv = new ModelAndView();
        mv.addObject("fiscalizacao", fiscalizacaoService.findAtual(dataAtual));
        return mv;
    }




}
