package com.maslen.controllers;

import com.maslen.dao.interfaces.PassengerDao;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PassengerController {
    private final PassengerDao passengerDao;

    public PassengerController(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    @RequestMapping(value = "/passengerMap", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public ModelAndView indexPassengerPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("passengerMap");
        return modelAndView;
    }

}
