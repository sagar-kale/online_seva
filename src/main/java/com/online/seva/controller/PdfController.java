package com.online.seva.controller;

import com.online.seva.util.PdfGenaratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PdfController {

    @Autowired
    private PdfGenaratorUtil pdfGenarator;

    @RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generatePdf(HttpServletResponse response) throws IOException {
        Map<String, Object> model = new HashMap<>();
        model.put("img", "img/profile.jpg");

        ByteArrayInputStream pdf = pdfGenarator.createPdf("/pdf/index.html", model);
        pdfGenarator.createPdf("/pdf/index.html", model);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=genereted.pdf");
        //headers.add("charset", "utf-8");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }
}