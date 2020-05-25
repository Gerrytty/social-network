package app.controller;

import app.dto.UploadForm;
import app.security.details.UserDetailsImpl;
import app.service.FilesUploadServiceImpl;
import app.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.annotation.MultipartConfig;

@Controller
@MultipartConfig
public class FilesController {

    private final FilesUploadServiceImpl filesUploadService;

    @Autowired
    public FilesController(FilesUploadServiceImpl filesUploadService) {
        this.filesUploadService = filesUploadService;
    }

    @PostMapping("/avaUpload")
    @PreAuthorize("isAuthenticated()")
    public String multiFileUpload(@ModelAttribute UploadForm form, Authentication authentication) {

        Logger.green_write("Post method from FilesController");

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        form.setUserId(userDetails.getUser().getUserId());
        filesUploadService.upload(form);

        return "redirect:/profile";

    }


}


