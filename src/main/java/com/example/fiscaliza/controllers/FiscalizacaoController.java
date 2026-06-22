package com.example.fiscaliza.controllers;

import com.example.fiscaliza.models.Fiscalizacao;
import com.example.fiscaliza.services.EntityValidator;
import com.example.fiscaliza.services.FiscalizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/fiscalizacao")
public class FiscalizacaoController {

    private final String LINK_DISABLED = "fiscalizacao";
    private final String REDIRECT_BASE = "/fiscalizacao/list";

    @Autowired
    FiscalizacaoService fiscalizacaoService;

    @Autowired
    EntityValidator entityValidator;

    @GetMapping("/list")
    public ModelAndView list(){

        ModelAndView mv = new ModelAndView();
        mv.addObject("linkDisabled", LINK_DISABLED);

        mv.addObject("fiscalizacoes", fiscalizacaoService.findAll());
        return mv;
    }

    @GetMapping("/new")
    public String nova(){
        return "/fiscalizacao/new";
    }

    @PostMapping("/create")
    public String create(Fiscalizacao fiscalizacao){

        var fiscalizacaoId = fiscalizacaoService.save(fiscalizacao);

        return "redirect:/fiscalizacao/" + fiscalizacaoId + "/show";
    }

    @GetMapping("/{id}/show")
    public ModelAndView show(@PathVariable Long id, RedirectAttributes attributes){
        ModelAndView mv = new ModelAndView("fiscalizacao/show");
        mv.addObject("linkDisabled", LINK_DISABLED);

        Fiscalizacao fiscalizacao = entityValidator.getOrTrhow(fiscalizacaoService.findById(id), REDIRECT_BASE);

        mv.addObject("fiscalizacao", fiscalizacao);
        return mv;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RedirectAttributes attributes){
        ModelAndView mv = new ModelAndView("fiscalizacao/edit");
        mv.addObject("linkDisabled", LINK_DISABLED);

        Fiscalizacao fiscalizacao = entityValidator.getOrTrhow(fiscalizacaoService.findById(id), REDIRECT_BASE);

        mv.addObject("fiscalizacao", fiscalizacao);
        return mv;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) throws RuntimeException {

        Fiscalizacao fiscalizacao = entityValidator.getOrTrhow(fiscalizacaoService.findById(id), REDIRECT_BASE);

        fiscalizacaoService.delete(fiscalizacao.getId());

        return "redirect:/fiscalizacao/list";
    }
}
