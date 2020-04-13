package app.controller;

import app.util.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AuthController {

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ModelAndView showAuthPage() {
        Logger.green_write("Get method from AuthController");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth");

        return modelAndView;
    }

}
