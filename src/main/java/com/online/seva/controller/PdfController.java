package com.online.seva.controller;

import com.online.seva.domain.Student;
import com.online.seva.domain.StudentImage;
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
import java.util.Base64;
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
            throw new RuntimeException("User not logged in");
        log.info("Under Pdf Report");
        log.info("Email :::" + email);
        Optional<Student> byEmail = studentService.findByEmail(email);
        if (!byEmail.isPresent())
            throw new RuntimeException("Email not present");
        if (!byEmail.get().isApproved())
            throw new RuntimeException("You are not authorize to access your doc.");
        Student student = byEmail.get();
        StudentImage studentImage = studentService.fetchImage(student);
        Map<String, Object> model = new HashMap<>();
        model.put("name", student.getName());
        model.put("course", student.getCourseName());
        String encodedString = Base64.getEncoder().encodeToString(studentImage.getImage());
        log.info("Img type::::" + studentImage.getImageType());
        model.put("imgType", studentImage.getImageType());
        model.put("img", encodedString);
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