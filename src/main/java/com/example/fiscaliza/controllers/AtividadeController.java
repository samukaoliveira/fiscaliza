package com.example.fiscaliza.controllers;

import com.example.fiscaliza.models.Atividade;
import com.example.fiscaliza.models.Empresa;
import com.example.fiscaliza.services.AtividadeService;
import com.example.fiscaliza.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/atividade")
public class AtividadeController {

    @Autowired
    AtividadeService atividadeService;

    @GetMapping("/list")
    public ModelAndView list(){

        ModelAndView mv = new ModelAndView();
        mv.addObject("atividades", atividadeService.findAll());
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

        Optional<Atividade> atividade = atividadeService.findById(id);

        if(atividade.isEmpty()){
            attributes.addFlashAttribute("message", "Atividade não encontrada com este id");
            return new ModelAndView("redirect:/atividade/list");
        }
        mv.addObject("atividade", atividade.get());
        return mv;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RedirectAttributes attributes){
        ModelAndView mv = new ModelAndView("atividade/edit");

        Optional<Atividade> atividade = atividadeService.findById(id);

        if(atividade.isEmpty()){
            attributes.addFlashAttribute("message", "Atividade não encontrada com este id");
            return new ModelAndView("redirect:/atividade/list");
        }
        mv.addObject("atividade", atividade.get());
        return mv;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) throws RuntimeException {

        Optional<Atividade> atividade = atividadeService.findById(id);

        atividadeService.delete(atividade.get().getId());

        return "redirect:/atividade/list";
    }
}
