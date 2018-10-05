package com.online.seva.controller;

import com.online.seva.domain.PasswordResetDto;
import com.online.seva.domain.PasswordResetToken;
import com.online.seva.domain.User;
import com.online.seva.repositories.jpa.PasswordResetTokenRepository;
import com.online.seva.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;

@Controller
@CrossOrigin
public class PasswordResetController {
    private static final Logger logger = LoggerFactory.getLogger(PasswordResetController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/reset-password", method = RequestMethod.GET)
    public String displayResetPasswordPage(@RequestParam(required = false) String token,
                                           Model model) {
        logger.info("under reset-password :: get");
        logger.info("token::" + token);
        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        logger.warn("token details from db::" + resetToken);
        if (resetToken == null) {
            model.addAttribute("error", "Could not find password reset token.");
        } else if (resetToken.isExpired()) {
            model.addAttribute("error", "Token has expired, please request a new password reset.");
        } else {
            model.addAttribute("token", resetToken.getToken());
        }
        logger.info("leaving display password reset page");
        return "reset-password";
    }

    @RequestMapping(value = "/reset-password", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Transactional
    public String handlePasswordReset(PasswordResetDto form, RedirectAttributes redirectAttributes, Model model) {
        logger.info("under handle password rest");
        logger.info("Password reset form:::" + form);

        if (null == form.getPassword() || null == form.getConfirmPassword() || null == form.getToken() || form.getPassword().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Please Check details");
            return "redirect:/reset-password?token=" + form.getToken();
        }
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("error", "Password and confirm password field not matches");
            return "redirect:/reset-password?token=" + form.getToken();
        }

        logger.info("Password reset details" + form);
        PasswordResetToken token = tokenRepository.findByToken(form.getToken());
        User user = token.getUser();
        String updatedPassword = passwordEncoder.encode(form.getPassword());
        logger.info("upading user::" + user);
        userService.updatePassword(updatedPassword, user.getUsername());
        tokenRepository.delete(token);
        model.addAttribute("reset", true);
        logger.info("leaving handlePassword reset");
        return "reset-password";
    }

}