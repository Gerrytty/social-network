package app.repository.interfaces;

import app.model.Message;

import java.util.List;

public interface MessagesRepository extends CrudRepository<Long, Message> {

    List<Message> findAllByUserId(Long userId);

}
