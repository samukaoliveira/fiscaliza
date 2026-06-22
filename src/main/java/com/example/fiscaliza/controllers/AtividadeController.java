package com.example.fiscaliza.controllers;

import com.example.fiscaliza.models.Atividade;
import com.example.fiscaliza.models.Empresa;
import com.example.fiscaliza.services.AtividadeService;
import com.example.fiscaliza.services.EmpresaService;
import com.example.fiscaliza.services.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/atividade")
public class AtividadeController {

    private final Object LINK_DISABLED = "atividade";
    private final String REDIRECT_BASE = "/atividade/list";

    @Autowired
    AtividadeService atividadeService;

    @Autowired
    EntityValidator entityValidator;

    @GetMapping("/list")
    public ModelAndView list(){

        ModelAndView mv = new ModelAndView();
        mv.addObject("atividades", atividadeService.findAll());
        mv.addObject("linkDisabled", LINK_DISABLED);
        return mv;
    }

    @GetMapping("/dependencia")
    @ResponseBody
    public List<Atividade> dependencia(){

        return atividadeService.findAll();
    }

    @GetMapping("/new")
    public String nova(){
        return "/atividade/new";
    }

    @PostMapping("/create")
    public String create(Atividade atividade){

        var atividadeId = atividadeService.save(atividade);

        return "redirect:/atividade/" + atividadeId + "/show";
    }

    @GetMapping("/{id}/show")
    public ModelAndView show(@PathVariable Long id, RedirectAttributes attributes){
        ModelAndView mv = new ModelAndView("atividade/show");
        mv.addObject("linkDisabled", LINK_DISABLED);

        Atividade atividade = entityValidator.getOrTrhow(atividadeService.findById(id), REDIRECT_BASE);

        mv.addObject("atividade", atividade);
        return mv;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RedirectAttributes attributes){
        ModelAndView mv = new ModelAndView("atividade/edit");
        mv.addObject("linkDisabled", LINK_DISABLED);

        Atividade atividade = entityValidator.getOrTrhow(atividadeService.findById(id), REDIRECT_BASE);

        mv.addObject("atividade", atividade);
        return mv;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id, RedirectAttributes attributes) throws RuntimeException {

        Atividade atividade = entityValidator.getOrTrhow(atividadeService.findById(id), REDIRECT_BASE);

        atividadeService.delete(atividade.getId());

        return "redirect:" + REDIRECT_BASE;
    }
}
