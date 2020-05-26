package app.config;

import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationContextConfig implements WebMvcConfigurer {

    @Bean(name = "viewResolver")
    public ViewResolver getViewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();

        viewResolver.setCache(true);
        viewResolver.setContentType("text/html; charset=utf-8");
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftl");
        viewResolver.setRequestContextAttribute("rc");

        return viewResolver;
    }

    @Bean(name = "freemarkerConfig")
    public FreeMarkerConfigurer getFreemarkerConfig() {
        FreeMarkerConfigurer config = new FreeMarkerConfigurer();

        // Folder containing FreeMarker templates.
        config.setTemplateLoaderPath("/WEB-INF/templates/");
        config.setDefaultEncoding("UTF-8");

        Properties properties = new Properties();

        properties.setProperty("auto_import", "spring.ftl as spring");
        config.setFreemarkerSettings(properties);

        return config;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        int maxUploadSizeInMb = 5 * 1024 * 1024;

        CommonsMultipartResolver cmr = new CommonsMultipartResolver();
        cmr.setMaxUploadSize(maxUploadSizeInMb * 2);
        cmr.setMaxUploadSizePerFile(maxUploadSizeInMb);

        return cmr;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}