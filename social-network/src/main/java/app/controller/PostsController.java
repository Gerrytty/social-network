package app.controller;

import app.dto.PostDto;
import app.model.User;
import app.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class PostsController {

    @Autowired
    PostServiceImpl postService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addPost(Authentication authentication, @ModelAttribute("post") PostDto postDto) {

        Optional<User> optionalUser = (Optional<User>) authentication.getPrincipal();

        postService.addPost(postDto, optionalUser.get());

        return "redirect:/profile";

    }

}
