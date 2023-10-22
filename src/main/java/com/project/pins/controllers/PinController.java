package com.project.pins.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.project.pins.entities.PinEntity;
import com.project.pins.models.services.IPinService;

@Controller
public class PinController {

    @Value("file:src/main/resources/static/img/pins/")
    private Resource directorioImagenes;

    @Autowired
    private IPinService iPinService;

    @GetMapping(value = "/index")
    public String listar(Model model) {

        model.addAttribute("titulo", "Nuestro Catalogo");
        model.addAttribute("pines", iPinService.findAll());
        return "index";
    }

    @GetMapping(value = "/nosotros")
    public String sobreNosotros(Model model) {

        model.addAttribute("titulo", "Sobre Nosotros");

        return "nosotros";
    }

    @GetMapping(value = "/faq")
    public String faq(Model model) {

        model.addAttribute("titulo", "Preguntas frecuentes");

        return "faq";
    }

    @GetMapping(value = "/asnjkdjbx/pins/admin/formularioUpdate")
    public String update(Model model) {

        model.addAttribute("titulo", "Sube tus productos");
        model.addAttribute("pin", new PinEntity());

        return "form_update";
    }

    @GetMapping(value = "/pwe2jbhj5/pinsManager/admin/managerFiles")
    public String managerFiles(Model model) {

        model.addAttribute("titulo", "Gestión de tus productos");
        model.addAttribute("pin", new PinEntity());
        model.addAttribute("pines", iPinService.findAll());

        return "managerFiles";
    }

    @PostMapping(value = "/registrarPin/admin/bnweuq")
    public String guardarPin(@Valid PinEntity pin,
            @RequestParam(value = "foto") MultipartFile foto,
            @RequestParam(value = "background") MultipartFile background, BindingResult result) {

        String nombreImagen = guardarImagen(foto);
        String nombreBackground = guardarImagen(background);

        if (result.hasErrors()) {
            return "error500";
        }

        pin.setImgPin(nombreImagen);
        pin.setFondoPin(nombreBackground);
        pin.setEstadoPin(true);
        iPinService.save(pin);

        return "redirect:/asnjkdjbx/pins/admin/formularioUpdate";
    }

    @RequestMapping(value = "/enviar-mensaje/{nombrePin}/{precioPin}", method = RequestMethod.GET)
    public ModelAndView enviarMensaje(@PathVariable(value = "nombrePin") String nombrePin,
            @PathVariable(value = "precioPin") String precioPin) {
        String msj = "Hola TusPinsTunja, estoy interesad@ en comprar el pin de " + nombrePin
                + " que tiene un precio de " + precioPin
                + " pesos. Si esta disponible, lo deseo adquirir, muchas gracias.";
        String whatsappUrl = "https://api.whatsapp.com/send?phone=+3015754422" + "&text=" + msj;
        return new ModelAndView(new RedirectView(whatsappUrl));
    }

    @RequestMapping(value = "/eliminarPin/{id}")
    public String eliminarById(@PathVariable(value = "id") Long id) throws IOException {
        PinEntity pin = iPinService.findById(id);

        String imagen = pin.getImgPin();
        Path pathArchivo = Paths.get(directorioImagenes.getFile().getAbsolutePath() + "/" + imagen);
        String fondo = pin.getFondoPin();
        Path pathArchivoFondo = Paths.get(directorioImagenes.getFile().getAbsolutePath() + "/" + fondo);

        if (id > 0) {
            Files.deleteIfExists(pathArchivo);
            Files.deleteIfExists(pathArchivoFondo);
            iPinService.deleteById(id);
        } else {
            return "redirect:/error500";
        }
        return "redirect:/pwe2jbhj5/pinsManager/admin/managerFiles";
    }

    @RequestMapping(value = "/eliminarPinEstado/{id}")
    public String eliminarByState(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            PinEntity pin = iPinService.findById(id);
            if (pin.getEstadoPin() == true) {

                pin.setEstadoPin(false);
                iPinService.save(pin);
            } else {
                pin.setEstadoPin(true);
                iPinService.save(pin);
            }
        } else {
            return "redirect:/error500";
        }
        return "redirect:/pwe2jbhj5/pinsManager/admin/managerFiles";
    }

    private String guardarImagen(MultipartFile imagen) {
        try {
            String nombreImagen = UUID.randomUUID().toString() + "_"
                    + StringUtils.cleanPath(imagen.getOriginalFilename());

            Path rutaCompleta = Paths.get(directorioImagenes.getFile().getAbsolutePath() + "/" + nombreImagen);
            Files.write(rutaCompleta, imagen.getBytes());
            return nombreImagen;
        } catch (IOException e) {
            return null;
        }
    }
}
