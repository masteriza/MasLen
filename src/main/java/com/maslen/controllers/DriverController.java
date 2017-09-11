package com.maslen.controllers;

import com.maslen.models.Route;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class DriverController {
    @RequestMapping(value = "/driverMap", method = RequestMethod.GET)
    //value = "/driverMap", method = RequestMethod.GET
    @PreAuthorize("hasRole('USER')")
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("driverMap");
        return modelAndView;
    }

    @PostMapping(value = "/saveDriverRoute")
    @PreAuthorize("hasRole('USER')")
    public ModelAndView saveDriverRoute(@Valid @RequestBody Route route, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("driverMap");
        return modelAndView;
    }


}
