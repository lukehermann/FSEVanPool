package com.springboot.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.springboot.model.User;
import com.springboot.service.MailClient;
import com.springboot.service.MailContentBuilder;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;

import java.util.UUID;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailClient mailClient;

    @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();

        model.setViewName("user/login");
        return model;
    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("user/signup");

        return model;
    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());

        if(userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if(bindingResult.hasErrors()) {
            model.setViewName("user/signup");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("user/login");
        }

        return model;
    }

    @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        String role=user.getRole();
        role=role.toLowerCase();
        model.setViewName("home/"+role);
        return model;
    }

    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }

    @RequestMapping(value= {"/retrievePassword"}, method=RequestMethod.GET)
    public ModelAndView retrievePassword(){
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        System.out.println("hellow world");
        model.setViewName("user/retrievePassword");
        return model;
    }

    @RequestMapping(value= {"/retrievePassword"}, method=RequestMethod.POST)
    public ModelAndView reset(@Valid User user, BindingResult bindingResult) throws MessagingException {
        System.out.println("hh");
        TemplateEngine engine = new TemplateEngine();
        System.out.println("1");

        ModelAndView model = new ModelAndView();
        System.out.println("2");

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        System.out.println("3");

        System.out.println("4");

        mailClient.send("xxxxxxxx@gmail.com", "Test", "Hello");
        System.out.println("5");

        model.setViewName("user/login");
        System.out.println("6");
        return model;
    }

    @RequestMapping(value = {"/payment"}, method= RequestMethod.POST)
    public ModelAndView payment(){
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        //model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        String role=user.getRole();
        role=role.toLowerCase();
        model.setViewName("home/payment");
        return model;
    }
}

