package app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import app.model.User;
import app.service.SubscribersServiceImpl;

import java.util.Optional;

@RestController
public class SubscribeRestController {

    private final SubscribersServiceImpl subscribersService;

    @Autowired
    public SubscribeRestController(SubscribersServiceImpl subscribersService) {
        this.subscribersService = subscribersService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/user/subscribe/{userId}")
    public ResponseEntity<?> subscribe(@PathVariable Long userId, Authentication authentication) {

        Optional<User> optionalUser = (Optional<User>) authentication.getPrincipal();
        User authUser = optionalUser.get();

        subscribersService.subscribe(authUser, new User(userId));
        return ResponseEntity.ok("200");
    }

}
