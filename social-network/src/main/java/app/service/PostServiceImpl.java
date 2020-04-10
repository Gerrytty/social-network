package app.service;

import app.dto.PostDto;
import app.model.Post;
import app.model.User;
import app.repository.interfaces.PostsRepository;
import app.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl {

    @Autowired
    PostsRepository postsRepository;

    public void addPost(PostDto postDto, User user) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        Logger.green_write("addPost method, date Now = " + dtf.format(now));

        Post post = Post.builder()
                .text(postDto.getText())
                .date(new Date())
                .authorId(user.getUserId())
                .whereId(user.getUserId())
                .build();

        postsRepository.save(post);

    }

    public List<Post> getPosts(long whereId) {
        return postsRepository.findAllByUserId(whereId);
    }

}
