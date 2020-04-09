package app.controller;

import app.dto.ProfileInfoDto;
import app.model.User;
import app.service.ProfileServiceImpl;
import app.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.rmi.NotBoundException;

@Controller
public class ProfileController {

    @Autowired
    ProfileServiceImpl profileService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView showReg(@RequestParam(defaultValue = "1") Long id) {

        Logger.green_write("GET METHOD FROM ProfileController");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");

        try {
            modelAndView.addObject("user", profileService.getProfileInfo(id));
            modelAndView.addObject("userId", 1);

        } catch (NotBoundException e) {

            e.printStackTrace();

            modelAndView.setViewName("error");
        }

        return modelAndView;
    }

}
