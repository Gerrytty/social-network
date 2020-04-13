package app.controller;

import app.repository.interfaces.UsersRepository;

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
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    ProfileServiceImpl profileService;

    @Autowired
    PostServiceImpl postService;

    @Autowired
    UsersRepository usersRepository;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView show(Authentication authentication, @RequestParam(defaultValue = "1") Long id) {

        Logger.green_write("GET METHOD FROM ProfileController");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");

        Optional<User> optionalUser = (Optional<User>) authentication.getPrincipal();

        long authUserId = optionalUser.get().getUserId();

        User user = new User();

        if(id == 1) {
            user = optionalUser.get();
            id = user.getUserId();
        }

        else {
            optionalUser = usersRepository.find(id);

            if(!optionalUser.isPresent()) {
                modelAndView.setViewName("error");
            }

            else {
                user = optionalUser.get();
            }

        }

        try {
            modelAndView.addObject("user", profileService.getProfileInfo(user));

            modelAndView.addObject("authUserId", authUserId);
            modelAndView.addObject("posts", postService.getPosts(id));
            modelAndView.addObject("id", id);

        } catch (NotBoundException e) {

            e.printStackTrace();

            modelAndView.setViewName("error");
        }

        return modelAndView;
    }

}
