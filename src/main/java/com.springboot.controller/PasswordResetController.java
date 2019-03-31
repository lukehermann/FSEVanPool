package com.springboot.controller;


import com.springboot.model.PasswordResetDto;
import com.springboot.model.PasswordResetToken;
import com.springboot.model.User;
import com.springboot.repository.PasswordResetTokenRepository;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/reset-password")
public class PasswordResetController {

    @Autowired private UserService userService;
    @Autowired private PasswordResetTokenRepository tokenRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute("passwordResetForm")
    public PasswordResetDto passwordReset() {
        return new PasswordResetDto();
    }

    @GetMapping
    public ModelAndView displayResetPasswordPage(@RequestParam(required = false) String token,
                                           Model model) {

        ModelAndView model1 = new ModelAndView();

        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null){
            model.addAttribute("error", "Could not find password reset token.");
        } else if (resetToken.isExpired()){
            model.addAttribute("error", "Token has expired, please request a new password reset.");
        } else {
            model.addAttribute("token", resetToken.getToken());
        }

        model1.setViewName("user/reset-password");
        return model1;
    }

    @PostMapping
    @Transactional
    public ModelAndView handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetDto form,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes) {

        ModelAndView model1 = new ModelAndView();

        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
            redirectAttributes.addFlashAttribute("passwordResetForm", form);
            model1.setViewName("redirect:/reset-password?token=" + form.getToken());
            return model1;
        }

        PasswordResetToken token = tokenRepository.findByToken(form.getToken());
        User user = token.getUser();
        String updatedPassword = passwordEncoder.encode(form.getPassword());
        userService.updatePassword(updatedPassword, user.getId());
        tokenRepository.delete(token);

        model1.setViewName("redirect:/login?resetSuccess");
        return model1;
    }

}