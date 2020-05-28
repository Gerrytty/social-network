package app.rest;

import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import app.dto.PostDto;
import app.model.Post;
import app.model.User;
import app.service.PostServiceImpl;

import java.util.Optional;

@RestController
public class PostsRestController {

    private final PostServiceImpl postService;

    @Autowired
    public PostsRestController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/api/post/{postId}/delete")
    public ResponseEntity deletePost(@PathVariable Long postId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> optionalUser = (Optional<User>) authentication.getPrincipal();
        User userDetails = optionalUser.get();

//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();

        try {
            postService.delete(postId, userDetails.getUserId());

            return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);

        } catch (AccessDeniedException e) {

            return new ResponseEntity<Error>(HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/api/addPost")
    public ResponseEntity<?> addPost(Authentication authentication, @RequestBody PostDto postDto) {

        Optional<User> optionalUser = (Optional<User>) authentication.getPrincipal();

        PostDto post = postService.addPost(postDto, optionalUser.get());

        return ResponseEntity.ok(post);

    }

}
