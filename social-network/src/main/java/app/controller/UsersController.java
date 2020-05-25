package app.controller;

import app.service.UsersServiceImpl;
import app.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsersController {

    private final UsersServiceImpl usersService;

    @Autowired
    public UsersController(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showAuthPage(Model model) {
        Logger.green_write("Get method from UsersController");
        model.addAttribute("users", usersService.allUsers());
        return "users";
    }

}
