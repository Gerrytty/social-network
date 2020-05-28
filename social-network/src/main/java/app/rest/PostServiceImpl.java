package ru.itis.socialnetworkboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.itis.socialnetworkboot.dto.PostDto;
import ru.itis.socialnetworkboot.model.Post;
import ru.itis.socialnetworkboot.model.User;
import ru.itis.socialnetworkboot.repository.interfaces.LikesRepository;
import ru.itis.socialnetworkboot.repository.interfaces.PostsRepository;
import ru.itis.socialnetworkboot.repository.interfaces.UsersRepository;

import java.util.*;

@Service
public class PostServiceImpl {

    private final PostsRepository postsRepository;
    private final LikesRepository likesRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public PostServiceImpl(PostsRepository postsRepository, LikesRepository likesRepository, UsersRepository usersRepository) {
        this.postsRepository = postsRepository;
        this.likesRepository = likesRepository;
        this.usersRepository = usersRepository;
    }

    public PostDto addPost(PostDto postDto, User user) {

        String text = postDto.getText();

        if(text.contains("@")) {
            text = replace(text);
        }

        Post post = Post.builder()
                .text(text)
                .date(new Date())
                .whereId(postDto.getPageId())
                .author(user)
                .build();

        return PostDto.from(postsRepository.save(post));

    }

    public List<PostDto> getPosts(long whereId) {

        List<Post> posts = postsRepository.findBywhereIdOrderByDateDesc(whereId);
        return getPostsWithLike(posts);
    }

    public List<PostDto> getPostsWithLike(List<Post> posts) {
        List<PostDto> postDtos = new ArrayList<>();

        for (Post post : posts) {
            PostDto postDto = PostDto.from(post);
            Long countOfLikes = likesRepository.countAllByPost(post);
            postDto.setCountOfLikes(countOfLikes);
            postDtos.add(postDto);
        }

        return postDtos;
    }

    public void delete(Long postId, Long authUserId) throws AccessDeniedException {

        Optional<Post> post = postsRepository.findById(postId);

        if(post.isPresent()) {
            if(post.get().getAuthor().getUserId().equals(authUserId)) {
                postsRepository.deleteById(postId);
            }
            else {
                throw new AccessDeniedException("You can't delete this post because you not author");
            }
        }
        else {
            throw new AccessDeniedException("You can't delete this post because you not author");
        }

    }

    private String replace(String text) {

        String finalText = "";

        if(text.contains("@")) {
            String[] strings = text.split(" ");
            Arrays.stream(strings).forEach(s -> System.out.println(s));
            for (int i = 0; i < strings.length; i++) {
                if(strings[i].charAt(0) == '@') {
                    Optional<User> user = usersRepository.findByEmail(strings[i].replaceFirst("@", ""));
                    if(user.isPresent()) {
                        Long id = user.get().getUserId();
                        finalText += "@" + "<a href='/profile?id=" + id + "'><p>" + strings[i] + "</p>" + "</a>";
                    }
                    else {
                        finalText += strings[i];
                    }
                }
                else {
                    finalText += strings[i];
                }
                if(i < strings.length - 1) {
                    finalText += " ";
                }
            }
        }

        return finalText;
    }

}
