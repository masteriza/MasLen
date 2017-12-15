package com.maslen.controllers;

import com.maslen.dao.interfaces.DriverDao;
import com.maslen.models.Route;
import com.maslen.models.RoutesResponseBody;
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
    //@PreAuthorize("hasRole('USER')")
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("driverMap");
        return modelAndView;
    }


    @PostMapping(value = "/saveDriverRoute")
    @PreAuthorize("hasRole('USER')")

    public RoutesResponseBody saveDriverRoute(@Valid @RequestBody Route route, BindingResult bindingResult) {

        RoutesResponseBody response = new RoutesResponseBody();

        driverDao.addRoute(route);

        List<Route> routes = driverDao.getAllRoute();

        response.setMessage("OK");
        response.setCode("200");
        response.setRoutes(routes);

        return response;
    }

    @DeleteMapping(value = "/deleteDriverRoute/{routeId}")
    @PreAuthorize("hasRole('USER')")

    public RoutesResponseBody deleteDriverRoute(/*@Valid*/ @PathVariable int routeId/*, BindingResult bindingResult*/) {

        RoutesResponseBody response = new RoutesResponseBody();

        driverDao.deleteRoute(routeId);

        List<Route> routes = driverDao.getAllRoute();

        response.setMessage("OK");
        response.setCode("200");
        response.setRoutes(routes);
        return response;
    }

}
