package app.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.dto.ProfileInfoDto;
import app.model.User;
import app.service.PostServiceImpl;
import app.service.ProfileServiceImpl;

@RestController
public class ProfileRestController {

    private final ProfileServiceImpl profileService;
    private final PostServiceImpl postService;

    public ProfileRestController(ProfileServiceImpl profileService,
                                 PostServiceImpl postService) {

        this.profileService = profileService;
        this.postService = postService;

    }

    @GetMapping(value = "/api/profile/{userId}")
    public ResponseEntity<?> getProfile(@PathVariable Long userId) {

        ProfileInfoDto profileInfoDto = profileService.getProfileInfo(new User(userId));
        profileInfoDto.setPosts(postService.getPosts(userId));

        return ResponseEntity.ok("200");
    }

}
