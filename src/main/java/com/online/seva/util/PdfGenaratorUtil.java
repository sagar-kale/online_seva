package com.online.seva.util;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@Component("pdfGenerator")
@Slf4j
public class PdfGenaratorUtil {

    @Autowired
    private Configuration freemarkerConfig;

    public ByteArrayInputStream createPdf(String templateName, Map map) {
        Assert.notNull(templateName, "The templateName can not be null");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Template t = freemarkerConfig.getTemplate(templateName);
            String processedHtml = FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setBaseUri("target/classes/templates/pdf/");
            PdfWriter pdfWriter = new PdfWriter(outputStream, new WriterProperties().setFullCompressionMode(true));
            HtmlConverter.convertToPdf(processedHtml, outputStream, converterProperties);
            /*ITextRenderer renderer = new ITextRenderer();
            renderer.getFontResolver().addFont(FontFactory.HELVETICA, BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(outputStream, false);
            renderer.finishPDF();*/
            System.out.println("PDF created successfully");
        } catch (Exception e) {
            log.error("error while converting::", e.getMessage());
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) { /*ignore*/ }
            }
        }
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
