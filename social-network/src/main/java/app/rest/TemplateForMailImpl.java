package ru.itis.socialnetworkboot.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ru.itis.socialnetworkboot.dto.Mail;

import java.io.IOException;

@Service
public class TemplateForMailImpl {

    private final Configuration freemarkerConfig;

    public TemplateForMailImpl(Configuration freemarkerConfig) {
        this.freemarkerConfig = freemarkerConfig;
    }

    public String getHtml(Mail mail, String templateName) {

        try {
            Template t = freemarkerConfig.getTemplate(templateName);
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

        return "";
    }

}