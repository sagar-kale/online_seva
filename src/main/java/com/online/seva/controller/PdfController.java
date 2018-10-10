package com.online.seva.controller;

import com.online.seva.domain.Student;
import com.online.seva.service.SessionService;
import com.online.seva.service.StudentService;
import com.online.seva.util.PdfGenaratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
public class PdfController {

    @Autowired
    private PdfGenaratorUtil pdfGenarator;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/pdfreport/{stdEmail}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity generatePdf(@PathVariable("stdEmail") String email, HttpServletRequest request) {
        if (sessionService.getLoggedUser(request) == null)
            throw new RuntimeException("user not logged in");
        log.info("Under Pdf Report");
        log.info("Email :::" + email);
        Optional<Student> byEmail = studentService.findByEmail(email);
        if (!byEmail.isPresent())
            throw new RuntimeException("email not present");
        Student student = byEmail.get();
        Map<String, Object> model = new HashMap<>();
        model.put("name", student.getName());
        model.put("course", student.getCourseName());
        model.put("img", "https://ae01.alicdn.com/kf/HTB1Ihqyacz85uJjSZFoq6xjcpXaS/QUEENS-KISS-Round-Kid-Sunglasses-Children-Boys-Girls-Cute-Mirror-Baby-Circle-Sun-Glasses-Flowers-Frame.jpg_640x640.jpg");
        model.put("grade", "71%");
        model.put("date", new Date(System.currentTimeMillis()));

        ByteArrayInputStream pdf = pdfGenarator.createPdf("/pdf/certificate.html", model);
        String filename = student.getEmail() + "_certificate.pdf";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + filename);
        //headers.add("charset", "utf-8");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }
}