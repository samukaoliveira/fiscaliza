package com.example.fiscaliza.controllers;

import com.example.fiscaliza.models.Empresa;
import com.example.fiscaliza.services.EmpresaService;
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

        Optional<Empresa> empresa = empresaService.findById(id);

        if(empresa.isEmpty()){
            attributes.addFlashAttribute("message", "Empresa não encontrada com este id");
            return new ModelAndView("redirect:/empresa/list");
        }
        mv.addObject("empresa", empresa.get());
        return mv;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RedirectAttributes attributes){
        ModelAndView mv = new ModelAndView("empresa/edit");

        Optional<Empresa> empresa = empresaService.findById(id);

        if(empresa.isEmpty()){
            attributes.addFlashAttribute("message", "Empresa não encontrada com este id");
            return new ModelAndView("redirect:/empresa/list");
        }
        mv.addObject("empresa", empresa.get());
        return mv;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) throws RuntimeException {

        Optional<Empresa> empresa = empresaService.findById(id);

        empresaService.delete(empresa.get().getId());

        return "redirect:/empresa/list";
    }
}
