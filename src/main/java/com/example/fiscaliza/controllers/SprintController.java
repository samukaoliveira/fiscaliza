package com.example.fiscaliza.controllers;

import com.example.fiscaliza.models.Sprint;
import com.example.fiscaliza.services.SprintService;
import com.example.fiscaliza.services.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/sprint")
public class SprintController {

    private final Object LINK_DISABLED = "sprint";
    private final String REDIRECT_BASE = "/sprint/list";

    @Autowired
    SprintService sprintService;

    @Autowired
    EntityValidator entityValidator;

    @GetMapping("/list")
    public ModelAndView list(){

        ModelAndView mv = new ModelAndView();
        mv.addObject("sprints", sprintService.findAll());
        mv.addObject("linkDisabled", LINK_DISABLED);
        return mv;
    }

    @GetMapping("/new")
    public String nova(){
        return "/sprint/new";
    }

    @PostMapping("/create")
    public String create(Sprint sprint){

        var sprintId = sprintService.save(sprint);

        return "redirect:/sprint/" + sprintId + "/show";
    }

    @GetMapping("/{id}/show")
    public ModelAndView show(@PathVariable Long id, RedirectAttributes attributes){
        ModelAndView mv = new ModelAndView("sprint/show");
        mv.addObject("linkDisabled", LINK_DISABLED);

        Sprint sprint = entityValidator.getOrTrhow(sprintService.findById(id), REDIRECT_BASE);

        mv.addObject("sprint", sprint);
        return mv;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RedirectAttributes attributes){
        ModelAndView mv = new ModelAndView("sprint/edit");
        mv.addObject("linkDisabled", LINK_DISABLED);

        Sprint sprint = entityValidator.getOrTrhow(sprintService.findById(id), REDIRECT_BASE);

        mv.addObject("sprint", sprint);
        return mv;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id, RedirectAttributes attributes) throws RuntimeException {

        Sprint sprint = entityValidator.getOrTrhow(sprintService.findById(id), REDIRECT_BASE);

        sprintService.delete(sprint.getId());

        return "redirect:" + REDIRECT_BASE;
    }
}
