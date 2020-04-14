package app.controller;

import app.dto.RegistrationDto;
import app.model.User;
import app.service.AuthServiceImpl;
import app.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    @Autowired
    AuthServiceImpl authService;

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public ModelAndView showReg() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("reg");
        return modelAndView;
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String register(Model model, @ModelAttribute("registration") RegistrationDto registrationDto) {
        Logger.green_write("Post method from regController");

        User user = authService.register(registrationDto);

        if(user != null) {
            return "redirect:/profile?id=" + user.getUserId();
        }

        return "redirect:/reg";

    }

}
