package ru.itis.socialnetworkboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ru.itis.socialnetworkboot.dto.PostDto;
import ru.itis.socialnetworkboot.model.Post;
import ru.itis.socialnetworkboot.model.Subscriptions;
import ru.itis.socialnetworkboot.model.User;
import ru.itis.socialnetworkboot.repository.interfaces.PostsRepository;
import ru.itis.socialnetworkboot.repository.interfaces.SubscriptionsRepository;

import java.util.List;

@Service
public class SubscribersServiceImpl {

    private final SubscriptionsRepository subscriptionsRepository;
    private final PostsRepository postsRepository;
    private final PostServiceImpl postService;

    @Autowired
    public SubscribersServiceImpl(SubscriptionsRepository subscriptionsRepository,
                                  PostsRepository postsRepository,
                                  PostServiceImpl postService) {
        this.subscriptionsRepository = subscriptionsRepository;
        this.postsRepository = postsRepository;
        this.postService = postService;
    }

    // все подписки
    public List<Subscriptions> getSubscriptions(User user) {
        return subscriptionsRepository.findAllByWho(user);
    }

    // все подписчики
    public List<Subscriptions> getSubscribers(User user) {
        return subscriptionsRepository.findAllByOnWhom(user);
    }

    public void subscribe(User who, User onWhom) {
        subscriptionsRepository.save(new Subscriptions(who, onWhom));
    }

    public List<PostDto> getPosts(Long whoId) {

        List<Post> posts = postsRepository.getSubPost(whoId);

        return postService.getPostsWithLike(posts);

    }

}
