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
import java.sql.Date;
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
        model.put("name", "Sagar Kale");
        model.put("course", "MS-CIT");
        model.put("img", "https://res.cloudinary.com/sgrkale/image/upload/v1538544230/twyctbv8k4zcvyxijref.jpg");
        model.put("grade", "71%");
        model.put("date", new Date(System.currentTimeMillis()));

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