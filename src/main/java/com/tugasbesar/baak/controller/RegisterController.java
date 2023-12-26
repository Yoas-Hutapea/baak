package com.tugasbesar.baak.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tugasbesar.baak.model.User;
import com.tugasbesar.baak.service.RegisterService;

@Controller
public class RegisterController {
	@Autowired
    private RegisterService userService;

                                   
    @GetMapping("/register")
           
    public ModelAndView register() {
    	ModelAndView mav = new ModelAndView("auth/register");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user ) {
        User oauthUser = userService.register(user.getUsername(), user.getPassword(),user.getRoles(),user.getNoktp(), user.getNim(),user.getNamalengkap(),user.getNohp());

        if(Objects.nonNull(oauthUser)) 
        {   
            return "redirect:/user/index";
        }else {
            return "redirect:/register";
        }

    }

    
}
