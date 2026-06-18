package com.example.fiscaliza.controllers;

import com.example.fiscaliza.models.Fiscalizacao;
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

    @Autowired
    FiscalizacaoService fiscalizacaoService;

    @GetMapping("/list")
    public ModelAndView list(){

        ModelAndView mv = new ModelAndView();
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

        Optional<Fiscalizacao> fiscalizacao = fiscalizacaoService.findById(id);

        if(fiscalizacao.isEmpty()){
            attributes.addFlashAttribute("message", "Fiscalizacao não encontrada com este id");
            return new ModelAndView("redirect:/fiscalizacao/list");
        }
        mv.addObject("fiscalizacao", fiscalizacao.get());
        return mv;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RedirectAttributes attributes){
        ModelAndView mv = new ModelAndView("fiscalizacao/edit");

        Optional<Fiscalizacao> fiscalizacao = fiscalizacaoService.findById(id);

        if(fiscalizacao.isEmpty()){
            attributes.addFlashAttribute("message", "Fiscalizacao não encontrada com este id");
            return new ModelAndView("redirect:/fiscalizacao/list");
        }
        mv.addObject("fiscalizacao", fiscalizacao.get());
        return mv;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) throws RuntimeException {

        Optional<Fiscalizacao> fiscalizacao = fiscalizacaoService.findById(id);

        fiscalizacaoService.delete(fiscalizacao.get().getId());

        return "redirect:/fiscalizacao/list";
    }
}
