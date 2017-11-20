package com.maslen.controllers;

import com.maslen.dao.interfaces.PassengerDao;
import com.maslen.models.AjaxResponseBody;
import com.maslen.models.PassengerSearchRouteDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    @PostMapping(value = "/searchRoute")
    @PreAuthorize("hasRole('USER')")

    public AjaxResponseBody deleteDriverRoute(@Valid @RequestBody PassengerSearchRouteDto passengerSearchRouteDto, BindingResult bindingResult) {

        AjaxResponseBody response = new AjaxResponseBody();

        passengerDao.searchRoute(passengerSearchRouteDto);

        return response;
    }

}
