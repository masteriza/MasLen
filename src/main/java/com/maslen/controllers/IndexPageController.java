package com.maslen.controllers;

import com.maslen.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class IndexPageController {
    private static final String NAME_MODEL = "user";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('USER')")
    //@PreAuthorize("hasAnyRole('OWNER', 'ADMIN')")
    public String indexPage() {
//        ModelAndView modelAndView = new ModelAndView();
//        User user = new User();
//        modelAndView.addObject(user);
//        modelAndView.setViewName("redirect:/indexPoint.html");
        return "index";
        //return "redirect:/indexPoint.html";

    }

    //    @RequestMapping("/registrationUser")
    @PostMapping("/registrationUser")
    public ModelAndView registrationUser(@Valid @ModelAttribute(NAME_MODEL)
                                                 User user, BindingResult bindingResult, ModelAndView modelAndView) {

        modelAndView.setViewName("indexPoint");
        return modelAndView;
    }

    @PostMapping("/loginUser")
    public ModelAndView loginUser(@Valid @ModelAttribute(NAME_MODEL)
                                          User user, BindingResult bindingResult, ModelAndView modelAndView) {

        modelAndView.setViewName("indexPoint");
        return modelAndView;
    }


}
