package com.maslen.controllers;

import com.maslen.dao.interfaces.DriverDao;
import com.maslen.models.Route;
import com.maslen.models.ValidationResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DriverController {
    private final DriverDao driverDao;

    public DriverController(DriverDao driverDao) {
        this.driverDao = driverDao;
    }


    @RequestMapping(value = "/driverMap", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("driverMap");
        return modelAndView;
    }

    @PostMapping(value = "/saveDriverRoute")
    @PreAuthorize("hasRole('USER')")
    public ValidationResponse saveDriverRoute(@Valid @RequestBody Route route, BindingResult bindingResult) {

        ValidationResponse response = new ValidationResponse();

        driverDao.addRoute(route);

        List<Route> routes = driverDao.getAllRoute();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("driverMap");
//        return modelAndView;

        return response;
    }


}
