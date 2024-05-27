package org.example.tourcrud.controller;

import org.example.tourcrud.model.Tour;
import org.example.tourcrud.model.TourForm;
import org.example.tourcrud.model.Type;
import org.example.tourcrud.service.ITourService;
import org.example.tourcrud.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private ITourService tourService;

    @Autowired
    private ITypeService typeService;

    @Value("${file-upload}")
    private String fileUpload;

    @ModelAttribute("types")
    public Iterable<Type> listTypes() {
        return typeService.findAll();
    }

    @GetMapping
    public ModelAndView listTour() {
        ModelAndView modelAndView = new ModelAndView("/tour/list");
        Iterable<Tour> tours = tourService.findAll();
        modelAndView.addObject("tours", tours);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/tour/create");
        modelAndView.addObject("tour", new Tour());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("tour") Tour tour,
                         RedirectAttributes redirectAttributes) {
        tourService.save(tour);
        redirectAttributes.addFlashAttribute("message", "Create new tour successfully");
        return "redirect:/tours";
    }

    @PostMapping("/save")
    public ModelAndView saveTour(@ModelAttribute TourForm tourForm) {
        MultipartFile multipartFile = tourForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(tourForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Tour tour = new Tour(tourForm.getId(), tourForm.getCode(), tourForm.getDestination(),
                tourForm.getPrice(), fileName, tourForm.getType());
        tourService.save(tour);
        ModelAndView modelAndView = new ModelAndView("/tour/create");
        modelAndView.addObject("tourForm", tourForm);
        modelAndView.addObject("message", "Created new tour successfully !");
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Tour> tour = tourService.findById(id);
        if (tour.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/tour/update");
            modelAndView.addObject("tour", tour.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("tour") Tour tour,
                         RedirectAttributes redirect) {
        tourService.save(tour);
        redirect.addFlashAttribute("message", "Update tour successfully");
        return "redirect:/tours";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirect) {
        tourService.remove(id);
        redirect.addFlashAttribute("message", "Delete tour successfully");
        return "redirect:/tours";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        Tour tour = tourService.findById(id).get();
        model.addAttribute("tour", tour);
        return "/tour/view";
    }
}
