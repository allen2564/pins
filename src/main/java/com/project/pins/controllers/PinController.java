package com.project.pins.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.pins.models.services.IPinService;

@Controller
public class PinController {

    @Autowired
    private IPinService iPinService;

    @GetMapping(value = "/index")
    public String listar(Model model) {

        model.addAttribute("titulo","Nuestro Catalogo");

        return "index";
    }
    @GetMapping(value = "/nosotros")
    public String sobreNosotros(Model model) {

        model.addAttribute("titulo","Sobre Nosotros");

        return "nosotros";
    }
    
    @GetMapping(value = "/faq")
    public String faq(Model model) {

        model.addAttribute("titulo","Preguntas frecuentes");

        return "faq";
    }
    
    @GetMapping(value = "/asnjkdjbx/asdhhjkb/jkasdb/formularioUpdate")
    public String update(Model model) {

        model.addAttribute("titulo","Sube tus productos");

        return "form_update";
    }

}
