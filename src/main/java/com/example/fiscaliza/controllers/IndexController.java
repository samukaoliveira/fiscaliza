package com.example.fiscaliza.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    public final String ROOT_TEMPLATE = "/index";

    public String index(){
        return ROOT_TEMPLATE;
    }


}
