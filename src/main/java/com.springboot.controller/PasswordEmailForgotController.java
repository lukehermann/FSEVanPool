//package com.springboot.controller;
//
//
//import com.springboot.model.Mail;
//import com.springboot.model.PasswordForgotDto;
//import com.springboot.model.PasswordResetToken;
//import com.springboot.model.User;
//import com.springboot.repository.PasswordResetTokenRepository;
//import com.springboot.service.EmailService;
//import com.springboot.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@Controller
//@RequestMapping("/forgot-passwordEmail")
//public class PasswordEmailForgotController {
//
//    @Autowired private UserService userService;
//    @Autowired private PasswordResetTokenRepository tokenRepository;
//    @Autowired private EmailService emailService;
//
//    @ModelAttribute("forgotPasswordForm")
//    public PasswordForgotDto forgotPasswordDto() {
//        return new PasswordForgotDto();
//    }
//
//    @GetMapping
//    public ModelAndView displayForgotPasswordPage() {
//        ModelAndView model = new ModelAndView();
//        model.setViewName("user/forgot-passwordEmail");
//        return model;
//    }
//
//    @PostMapping
//    public ModelAndView processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
//                                            BindingResult result,
//                                            HttpServletRequest request) {
//
//        ModelAndView model1 = new ModelAndView();
//
//
//        if (result.hasErrors()){
//            model1.setViewName("user/forget-passwordEmail");
//            return model1;
//        }
//
//        User user = userService.findUserByEmail(form.getEmail());
//        if (user == null){
//            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
//            model1.setViewName("user/forgot-passwordEmail");
//            return model1;
//        }
//
//        PasswordResetToken token = new PasswordResetToken();
//        token.setToken(UUID.randomUUID().toString());
//        token.setUser(user);
//        token.setExpiryDate(30);
//        tokenRepository.save(token);
//
//        Mail mail = new Mail();
//        mail.setFrom("iowavanpool@gmail.com");
//        mail.setTo(user.getEmail());
//        mail.setSubject("Password reset request");
//
//        Map<String, Object> model = new HashMap<>();
//        model.put("token", token);
//        model.put("user", user);
//        model.put("signature", "https://memorynotfound.com");
//        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
//        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
//        mail.setModel(model);
//        emailService.sendEmail(mail);
//
//        model1.setViewName("redirect:/forgot-password?success");
//        return model1;
//
//    }
//
//}