package com.maslen.controllers;


import com.maslen.beans.Driver;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SaveDriverRoute {
    @PostMapping("/saveDriverRoute")
    public ModelAndView indexPage(@RequestBody Driver driver) {

//       String firstName = request.getParameter("firstName");
//        String lastName = request.getParameter("lastName");
//        String email = request.getParameter("email");

        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
