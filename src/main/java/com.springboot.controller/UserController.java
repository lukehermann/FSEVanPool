package com.springboot.controller;

import com.springboot.model.*;
import com.springboot.repository.PasswordResetTokenRepository;
import com.springboot.service.RouteService;
import com.springboot.service.UserService;
import com.springboot.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private VehicleService vehicleService;


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
        }
        else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("user/login");
        }

        return model;
    }

    @Autowired private PasswordResetTokenRepository tokenRepository;


    @ModelAttribute("passwordResetForm")
    public PasswordResetDto passwordReset() {
        return new PasswordResetDto();
    }

    @RequestMapping(value= {"/forgot-questions"}, method=RequestMethod.GET)
    public ModelAndView displayForgotQuestions(@RequestParam(required = false) String token,
                                               Model model) {

        ModelAndView model1 = new ModelAndView();

        PasswordForgotDto forgotDto = new PasswordForgotDto();

        PasswordResetToken resetToken = tokenRepository.findByToken(token);

        if (resetToken == null){
            model.addAttribute("error", "Could not find password reset token.");
        } else if (resetToken.isExpired()){
            model.addAttribute("error", "Token has expired, please request a new password reset.");
        } else {
            model.addAttribute("token", resetToken.getToken());
        }


        PasswordResetToken tokenUser = tokenRepository.findByToken(token);
        User user = tokenUser.getUser();

        forgotDto.setEmail(user.getEmail());
        forgotDto.setToken(resetToken.getToken());

        model1.addObject("forgotDto", forgotDto);

        model1.addObject("questionOne", user.getQuestionOne());
        model1.addObject("questionTwo", user.getQuestionTwo());
        model1.addObject("questionThree", user.getQuestionThree());

        model1.setViewName("user/forgot-questions");

        return model1;
    }

    @RequestMapping(value= {"/forgot-questions"}, method=RequestMethod.POST)
    public ModelAndView checkQuestions (@Valid PasswordForgotDto forgotDto, BindingResult bindingResult,  HttpServletRequest request) {

        ModelAndView model1 = new ModelAndView();
        User user = userService.findUserByEmail(forgotDto.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if( !encoder.matches(forgotDto.getQuestionOneAnswer(), user.getAnswerOne())) {
            bindingResult.rejectValue("questionOneAnswer", "error.forgotDto", "You have answered incorrectly!");
        }
        if( !encoder.matches(forgotDto.getQuestionTwoAnswer(), user.getAnswerTwo())) {
            bindingResult.rejectValue("questionTwoAnswer", "error.forgotDto", "You have answered incorrectly!");
        }
        if( !encoder.matches(forgotDto.getQuestionThreeAnswer(), user.getAnswerThree())) {
            bindingResult.rejectValue("questionThreeAnswer", "error.forgotDto", "You have answered incorrectly!");
        }


        if(bindingResult.hasErrors()) {
            model1.setViewName("redirect:/forgot-questions?token=" + forgotDto.getToken() + "&error=true");
        }
        else {
            model1.addObject("msg", "Answered Questions Correctly!");
            //model.setViewName("redirect:/reset-password?token=" + forgotDto.getToken());
            PasswordResetToken token = new PasswordResetToken();
            token.setToken(UUID.randomUUID().toString());
            token.setUser(user);
            token.setExpiryDate(5);
            tokenRepository.save(token);


            Map<String, Object> model = new HashMap<>();
            model.put("token", token);
            model.put("user", user);
            model.put("signature", "VanPool Iowa");
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
            model1.setViewName("redirect:/reset-password?token=" + token.getToken());
        }

        return model1;
    }




    @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        String role=user.getRole();
        role=role.toLowerCase();
        model.addObject("routeList", routeService.listAll());
        model.addObject("vehicleList", vehicleService.listAll());

        List<String> vehicleTypes = new ArrayList<>();
        vehicleTypes.add("SUV");
        vehicleTypes.add("Van");
        vehicleTypes.add("XL Van");
        vehicleTypes.add("Car");
        model.addObject("vehicleTypes", vehicleTypes);
        model.setViewName("home/"+role);
        return model;
    }

    @RequestMapping(value= {"/deleteRoute"}, method=RequestMethod.GET)
    public ModelAndView deleteRoute(@RequestParam("routes") List<String> routeids) {
        ModelAndView model = new ModelAndView();


        if(routeids != null){
            for(String id : routeids){
                int routeid = Integer.parseInt(id);
                routeService.deleteRoute(routeid);
            }
        }

        model.addObject("routeList", routeService.listAll());

        model.setViewName("redirect:/home/home");

        return model;
    }

    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
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
    @RequestMapping(value= {"/signUpRiderRoute"}, method=RequestMethod.GET)
    public ModelAndView signUpRiderRoute(@RequestParam("routes") List<String> routeids) {
        ModelAndView model = new ModelAndView();

        if(routeids != null){
            for(String id : routeids){
                int routeid = Integer.parseInt(id);
                System.out.println(routeid);
                routeService.signUpRiderRoute(routeid);
                //routeService.deleteRoute(routeid);
            }
        }

        model.addObject("routeList", routeService.listAll());

        model.setViewName("redirect:/home/home");

        return model;
    }

    @RequestMapping(value= {"/addVehicle"}, method=RequestMethod.GET)
    public ModelAndView deleteRoute(@RequestParam("vehicleType") String vehicleType) {
        ModelAndView model = new ModelAndView();

        if(vehicleType.equals("SUV")){
            Vehicle vech = new Vehicle();
            vech.setCapacity(7);
            vech.setType("SUV");
            vehicleService.saveVehicle(vech);

        }

        else if(vehicleType.equals("Van")){
            Vehicle vech = new Vehicle();
            vech.setCapacity(11);
            vech.setType("Van");
            vehicleService.saveVehicle(vech);

        }

        else if(vehicleType.equals("XL Van")){
            Vehicle vech = new Vehicle();
            vech.setCapacity(14);
            vech.setType("XL Van");
            vehicleService.saveVehicle(vech);

        }

        else if(vehicleType.equals("Car")){
            Vehicle vech = new Vehicle();
            vech.setCapacity(4);
            vech.setType("Car");
            vehicleService.saveVehicle(vech);
        }


        model.setViewName("redirect:/home/home");
        return model;
    }

}