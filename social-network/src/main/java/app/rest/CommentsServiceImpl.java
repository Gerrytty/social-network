package ru.itis.socialnetworkboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.socialnetworkboot.dto.CommentDto;
import ru.itis.socialnetworkboot.model.Comment;
import ru.itis.socialnetworkboot.model.Post;
import ru.itis.socialnetworkboot.model.User;
import ru.itis.socialnetworkboot.repository.interfaces.CommentsRepository;

import java.util.List;

@Service
public class CommentsServiceImpl {

    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsServiceImpl(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public void addComment(CommentDto commentDto, User user) {

        Comment comment = Comment.builder()
                .commentAuthor(user)
                .post(new Post(commentDto.getPostId()))
                .text(commentDto.getText())
                .build();

        commentsRepository.save(comment);

    }

    public List<CommentDto> getComments(Long postId, Integer page) {

        Post post = new Post(postId);

        return CommentDto.from(commentsRepository.findAllByPost(post));
    }

}
