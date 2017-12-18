package com.maslen.controllers;

import com.maslen.dao.interfaces.DriverDao;
import com.maslen.models.Route;
import com.maslen.models.RoutesResponseBody;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DriverController {
    private final DriverDao driverDao;

    public DriverController(DriverDao driverDao) {
        this.driverDao = driverDao;
    }


    @GetMapping(value = "/driverMap")
    //PreAuthorize("hasRole('ADMIN')")
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/driverMap");
        return modelAndView;
    }
//    public String indexPage() {
//        return "/driverMap";
//    }

//    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
//    public String redirect() {
//        return "redirect:finalPage";
//    }

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
