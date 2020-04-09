package app.controller;

import org.springframework.security.core.Authentication;
import app.model.User;
import app.service.PostServiceImpl;
import app.service.ProfileServiceImpl;
import app.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    PostServiceImpl postService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView show(@RequestParam(defaultValue = "1") Long id) {

        Logger.green_write("GET METHOD FROM ProfileController");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");

//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        User user = userDetails.getUser();
//        model.addAttribute("user", user);

        try {
            modelAndView.addObject("user", profileService.getProfileInfo(id));

            // TODO: replace 1
            modelAndView.addObject("userId", 1);
            modelAndView.addObject("posts", postService.getPosts(1));

        } catch (NotBoundException e) {

            e.printStackTrace();

            modelAndView.setViewName("error");
        }

        return modelAndView;
    }

}
