package com.maslen.controllers;

import com.maslen.models.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private static final String INDEX = "index";
    private static final String LOGIN_SUCCESS = "userPanel";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('USER')")
    //@PreAuthorize("hasAnyRole('OWNER', 'ADMIN')")
    public String indexPage() {
        String view = INDEX;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            view = LOGIN_SUCCESS;
        }
        return view;
    }

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
