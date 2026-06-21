package com.example.fiscaliza.controllers;

import com.example.fiscaliza.models.Empresa;
import com.example.fiscaliza.services.EmpresaService;
import com.example.fiscaliza.services.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @Autowired
    EntityValidator entityValidator;

    @GetMapping("/list")
    public ModelAndView list(){

        ModelAndView mv = new ModelAndView();
        mv.addObject("empresas", empresaService.findAll());
        return mv;
    }

    @GetMapping("/new")
    public String nova(){
        return "/empresa/new";
    }

    @PostMapping("/create")
    public String create(Empresa empresa){

        var empresaId = empresaService.save(empresa);

        return "redirect:/empresa/" + empresaId + "/show";
    }

    @GetMapping("/{id}/show")
    public ModelAndView show(@PathVariable Long id, RedirectAttributes attributes){
        ModelAndView mv = new ModelAndView("empresa/show");

        Empresa empresa = entityValidator.getOrTrhow(empresaService.findById(id), "/empresa/list");

        mv.addObject("empresa", empresa);
        return mv;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RedirectAttributes attributes){
        ModelAndView mv = new ModelAndView("empresa/edit");

        Empresa empresa = entityValidator.getOrTrhow(empresaService.findById(id), "/empresa/list");

        mv.addObject("empresa", empresa);
        return mv;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id, RedirectAttributes attributes) throws RuntimeException {

        Empresa empresa = entityValidator.getOrTrhow(empresaService.findById(id), "/empresa/list");

        empresaService.delete(empresa.getId());
        return "redirect:/empresa/list";
    }
}
