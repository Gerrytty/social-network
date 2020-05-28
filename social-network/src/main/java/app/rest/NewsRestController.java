package app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import app.dto.PostDto;
import app.model.User;
import app.service.SubscribersServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
public class NewsRestController {

    private final SubscribersServiceImpl subscribersService;

    @Autowired
    public NewsRestController(SubscribersServiceImpl subscribersService) {
        this.subscribersService = subscribersService;
    }

    @GetMapping("/api/news")
    public ResponseEntity<List<PostDto>> getNews(Authentication authentication) {

        Optional<User> optionalUser = (Optional<User>) authentication.getPrincipal();
        User user = optionalUser.get();

        return ResponseEntity.ok(subscribersService.getPosts(user.getUserId()));
    }

}
