package com.maslen.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DriverMapController {
    //    @RequestMapping("/driverMap")
//    @PreAuthorize("hasRole('USER')")
    public ModelAndView indexPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("driverMap.html");
        return modelAndView;
    }


}
