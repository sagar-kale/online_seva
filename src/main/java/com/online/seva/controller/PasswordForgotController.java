package com.online.seva.controller;

import com.online.seva.domain.Email;
import com.online.seva.domain.PasswordResetToken;
import com.online.seva.domain.User;
import com.online.seva.repositories.jpa.PasswordResetTokenRepository;
import com.online.seva.service.EmailService;
import com.online.seva.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@CrossOrigin
public class PasswordForgotController {
    private static final Logger logger = LoggerFactory.getLogger(PasswordForgotController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
    public String displayForgotPasswordPage() {
        return "forgot-password";
    }

    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Transactional
    public String processForgotPasswordForm(String email, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        logger.info("under processForgotPasswordForm");
        logger.info("Email :: " + email);
        if (email == null) {
            model.addAttribute("errorMsg", "Email Address invalid");
            return "forgot-password";
        }

        User user = userService.findByUsername(email);
        if (user == null) {
            model.addAttribute("errorMsg", "We could not find an account for that e-mail address.");
            return "forgot-password";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);

        Email mail = new Email();
        mail.setFrom("no-reply@online-seva.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset request");

        Map<String, Object> emailModel = new HashMap<>();
        emailModel.put("token", token);
        emailModel.put("name", user.getName());
        emailModel.put("signature", "https://online-seva.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        emailModel.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(emailModel);
        if (!emailService.sendSimpleMessage(mail)) {
            model.addAttribute("errorMsg", "Something went wront !! please contact administrator");
        }
        redirectAttributes.addFlashAttribute("reset", true);
        logger.info("Leaving processForgotPasswordForm");
        return "redirect:/forgot-password";

    }
}