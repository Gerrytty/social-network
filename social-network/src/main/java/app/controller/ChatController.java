package app.controller;

import app.model.User;
import app.service.MessagesServiceImpl;
import app.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ChatController {

    @Autowired
    MessagesServiceImpl messagesService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public ModelAndView show(Authentication authentication) {

        Logger.green_write("GET METHOD FROM ChatController");

        Optional<User> optionalUser = (Optional<User>) authentication.getPrincipal();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("chat");

        modelAndView.addObject("pageId", optionalUser.get().getUserId());
        modelAndView.addObject("allMessages", messagesService.getMessages());

        return modelAndView;
    }

}