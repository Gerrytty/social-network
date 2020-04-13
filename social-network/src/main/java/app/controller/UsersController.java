package app.controller;

import app.service.UsersServiceImpl;
import app.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsersController {

    @Autowired
    UsersServiceImpl usersService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView showAuthPage() {
        Logger.green_write("Get method from UsersController");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("users", usersService.allUsers());

        return modelAndView;
    }

}
