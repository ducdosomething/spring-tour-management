package org.example.tourcrud.controller;

import org.example.tourcrud.model.Tour;
import org.example.tourcrud.model.Type;
import org.example.tourcrud.service.ITourService;
import org.example.tourcrud.service.ITypeService;
import org.example.tourcrud.service.impl.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/types")
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @Autowired
    private ITourService tourService;

    @GetMapping
    public ModelAndView listType() {
        ModelAndView modelAndView = new ModelAndView("/type/list");
        Iterable<Type> types = typeService.findAll();
        modelAndView.addObject("types", types);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/type/create");
        modelAndView.addObject("type", new Type());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("type") Type type,
                         RedirectAttributes redirectAttributes) {
        typeService.save(type);
        redirectAttributes.addFlashAttribute("message", "Create new type successfully");
        return "redirect:/types";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Type> type = typeService.findById(id);
        if (type.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/type/update");
            modelAndView.addObject("type", type.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("type") Type type,
                         RedirectAttributes redirect) {
        typeService.save(type);
        redirect.addFlashAttribute("message", "Update type successfully");
        return "redirect:/types";
    }

    @GetMapping("/view-type/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Optional<Type> typeOptional = typeService.findById(id);
        if(!typeOptional.isPresent()){
            return new ModelAndView("/error_404");
        }

        Iterable<Tour> tours = tourService.findAllByType(typeOptional.get());

        ModelAndView modelAndView = new ModelAndView("/tour/list");
        modelAndView.addObject("tours", tours);
        return modelAndView;
    }
}
