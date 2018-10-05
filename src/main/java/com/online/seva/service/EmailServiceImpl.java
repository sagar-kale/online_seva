package com.online.seva.service;

import com.online.seva.domain.Email;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service("emailService")
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public boolean sendSimpleMessage(Email email) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Template t = freemarkerConfig.getTemplate("email-template.html");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, email.getModel());

            helper.setTo(email.getTo());
            helper.setText(html, true);
            helper.setSubject(email.getSubject());
            helper.setFrom(email.getFrom());

            emailSender.send(message);
        } catch (Exception e) {
            log.error("error occured while sending mail...", e);
            return false;
        }

        return true;
    }
}
