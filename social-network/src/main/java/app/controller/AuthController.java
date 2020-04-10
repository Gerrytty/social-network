package app.controller;

import app.dto.AuthenticationDto;
import app.model.User;
import app.service.AuthServiceImpl;
import app.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.rmi.NotBoundException;

@Controller
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ModelAndView showAuthPage() {
        Logger.green_write("Get method from AuthController");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth");

        return modelAndView;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String auth(@ModelAttribute("authentication") AuthenticationDto authenticationDto) {
        Logger.green_write("Post method from authController");

//        System.out.println("BBBBBBBBBBBBBBB");

        User user;

        try {
            user = authService.auth(authenticationDto);
        } catch (NotBoundException e) {
            return "redirect:/auth";
        }


        return "redirect:/profile";

    }

}
