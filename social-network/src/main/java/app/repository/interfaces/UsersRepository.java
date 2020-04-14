package app.repository.interfaces;

import app.model.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<Long, User> {

    Optional<User> findByEmail(String email);

    void updateAvaPath(Long userId, String newPath);

}
