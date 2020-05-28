package app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import app.dto.CommentForPhotoDto;
import app.dto.PhotoDto;
import app.model.User;
import app.service.AlbumsServiceImpl;
import app.service.CommentForPhotoServiceImpl;
import app.service.PhotoService;

import java.util.List;
import java.util.Optional;

@RestController
public class AlbumsRestController {

    private final AlbumsServiceImpl albumsService;
    private final PhotoService photoService;
    private final CommentForPhotoServiceImpl commentForPhotoService;

    @Autowired
    public AlbumsRestController(AlbumsServiceImpl albumsService,
                                PhotoService photoService,
                                CommentForPhotoServiceImpl commentForPhotoService) {
        this.albumsService = albumsService;
        this.photoService = photoService;
        this.commentForPhotoService = commentForPhotoService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/api/users/{userId}/albums")
    public ResponseEntity<?> getAllAlbums(Authentication authentication,
                             @PathVariable Long userId) {

        Optional<User> optionalUser = (Optional<User>) authentication.getPrincipal();

        return ResponseEntity.ok(albumsService.getAllByUser(optionalUser.get(), userId));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/api/newAlbum")
    public ResponseEntity<?> newAlbum(Authentication authentication, @RequestBody String title) {

        Optional<User> optionalUser = (Optional<User>) authentication.getPrincipal();
        albumsService.createAlbum(optionalUser.get(), title);

        return ResponseEntity.ok("200");
    }

    @GetMapping("/api/album/{albumId}/photos")
    public ResponseEntity<?> getPhotosInAlbum(@PathVariable("albumId") Long albumId) {
        List<PhotoDto> photoDtos =  photoService.getAlbumsPhotos(albumId);
        return ResponseEntity.ok(photoDtos);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/api/photo/{photoId}/comment")
    public ResponseEntity<?> leaveCommentToPhoto(Authentication authentication,
                                      @PathVariable("photoId") Long photoId,
                                      @RequestBody String text) {

        Optional<User> optionalUser = (Optional<User>) authentication.getPrincipal();

        commentForPhotoService.leaveComment(optionalUser.get(), photoId, text);

        return ResponseEntity.ok("200");
    }


    @GetMapping("/api/photo/{photoId}")
    public ResponseEntity<?> getComments(@PathVariable("photoId") Long photoId) {
        return ResponseEntity.ok(commentForPhotoService.getComments(photoId));
    }

}

