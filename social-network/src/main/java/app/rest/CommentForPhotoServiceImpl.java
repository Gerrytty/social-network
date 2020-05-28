package ru.itis.socialnetworkboot.service;

import org.springframework.stereotype.Service;
import ru.itis.socialnetworkboot.dto.CommentDto;
import ru.itis.socialnetworkboot.dto.CommentForPhotoDto;
import ru.itis.socialnetworkboot.model.CommentForPhoto;
import ru.itis.socialnetworkboot.model.Photo;
import ru.itis.socialnetworkboot.model.User;
import ru.itis.socialnetworkboot.repository.interfaces.CommentsForPhotoRepository;

import java.util.List;

@Service
public class CommentForPhotoServiceImpl {

    private final CommentsForPhotoRepository commentsForPhotoRepository;

    public CommentForPhotoServiceImpl(CommentsForPhotoRepository commentsForPhotoRepository) {
        this.commentsForPhotoRepository = commentsForPhotoRepository;
    }

    public void leaveComment(User user, Long photoId, String text) {

        CommentForPhoto commentForPhoto = CommentForPhoto.builder()
                .commentAuthor(user)
                .photo(new Photo(photoId))
                .text(text)
                .build();

        commentsForPhotoRepository.save(commentForPhoto);
    }

    public List<CommentForPhotoDto> getComments(Long photoId) {
        return CommentForPhotoDto.from(commentsForPhotoRepository.findAllByPhoto(new Photo(photoId)));
    }

}
