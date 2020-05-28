package app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import app.model.User;
import app.service.LikesServiceImpl;

import java.util.Optional;

@RestController
public class LikesRestController {

    private final LikesServiceImpl likesService;

    @Autowired
    public LikesRestController(LikesServiceImpl likesService) {
        this.likesService = likesService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/{postId}/like")
    public void like(@PathVariable Long postId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> optionalUser = (Optional<User>) authentication.getPrincipal();
        User user = optionalUser.get();

        likesService.saveLike(postId, user);

    }

}
