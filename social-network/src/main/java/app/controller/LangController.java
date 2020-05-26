package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LangController {

    @GetMapping("/lang")
    public String changeLang(HttpServletResponse response, @RequestParam String lang) {
        response.addCookie(new Cookie("localeInfo", lang));
        return "redirect:/";
    }

}
