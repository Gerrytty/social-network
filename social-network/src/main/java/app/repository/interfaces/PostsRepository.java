package app.repository.interfaces;

import app.model.Post;

import java.util.List;

public interface PostsRepository extends CrudRepository<Long, Post> {

    List<Post> findAllByUserId(Long userId);

}
