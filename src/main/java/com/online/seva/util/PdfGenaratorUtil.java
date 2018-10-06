package com.online.seva.util;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@Service("pdfGenerator")
public class PdfGenaratorUtil {

    @Autowired
    private Configuration freemarkerConfig;

    public ByteArrayInputStream createPdf(String templateName, Map map) {
        Assert.notNull(templateName, "The templateName can not be null");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Template t = freemarkerConfig.getTemplate(templateName);
            String processedHtml = FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
            ITextRenderer renderer = new ITextRenderer();
            renderer.getFontResolver().addFont("static/font/Shivaji01.ttf", BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(outputStream, false);
            renderer.finishPDF();
            System.out.println("PDF created successfully");
        } catch (Exception e) {
            e.printStackTrace();
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
