package app.controller;

import app.repository.interfaces.UsersRepository;

import app.security.details.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import app.model.User;
import app.service.PostServiceImpl;
import app.service.ProfileServiceImpl;
import app.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProfileController {

    private final ProfileServiceImpl profileService;
    private final PostServiceImpl postService;
    private final UsersRepository usersRepository;

    @Autowired
    public ProfileController(ProfileServiceImpl profileService, PostServiceImpl postService, UsersRepository usersRepository) {
        this.profileService = profileService;
        this.postService = postService;
        this.usersRepository = usersRepository;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String show(Authentication authentication, @RequestParam(defaultValue = "1") Long id, Model model) {

        Logger.green_write("Get method from ProfileController");

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        long authUserId = userDetails.getUser().getUserId();

        User user = new User();

        if(id == 1) {
            user = userDetails.getUser();
            id = user.getUserId();
        }

        else {
            Optional<User> optionalUser = usersRepository.find(id);

            if(!optionalUser.isPresent()) {
                model.addAttribute("error");
            }

            else {
                user = optionalUser.get();
            }

        }

        model.addAttribute("user", profileService.getProfileInfo(user));
        model.addAttribute("authUserId", authUserId);
        model.addAttribute("posts", postService.getPosts(id));
        model.addAttribute("id", id);

        return "profile";
    }

}
