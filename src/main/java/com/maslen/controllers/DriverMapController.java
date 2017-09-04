package com.maslen.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DriverMapController {
    @RequestMapping("/driverMap")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("driverMap");
        return modelAndView;
    }


}
