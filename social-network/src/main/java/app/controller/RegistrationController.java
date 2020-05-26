package app.controller;

import app.dto.RegistrationDto;
import app.model.User;
import app.service.AuthServiceImpl;
import app.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final AuthServiceImpl authService;

    public RegistrationController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String showReg(Model model) {

        Logger.green_write("Get method from RegistrartionController");

        model.addAttribute("registrationDto", new RegistrationDto());

        return "reg";
    }


    @PostMapping(value = "/reg")
    public String register(@Valid @ModelAttribute("registrationDto")
                                       RegistrationDto registrationDto,
                           BindingResult bindingResult) {

        Logger.green_write("Post method from regController");

        User user;

        if(bindingResult.hasErrors()) {
            Logger.red_write("HAS ERRORS");
            return "reg";
        }

        else {
            Logger.green_write("NO ERRORS");
            user = authService.register(registrationDto);
        }

        if(user != null) {
            return "redirect:/profile?id=" + user.getUserId();
        }

        return "redirect:/reg";

    }

}
