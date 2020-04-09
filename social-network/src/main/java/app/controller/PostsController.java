package app.controller;

import app.dto.PostDto;
import app.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostsController {

    @Autowired
    PostServiceImpl postService;

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("post") PostDto postDto) {

        postService.addPost(postDto);

        return "redirect:/profile";

    }

}
