package app.service;

import app.dto.MessageDto;
import app.model.Message;
import app.repository.interfaces.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesServiceImpl {

    @Autowired
    MessagesRepository messagesRepository;

    public void addMessage(MessageDto messageDto) {

        Message message = Message.builder()
                .text(messageDto.getText())
                .userId(Long.parseLong(messageDto.getPageId()))
                .build();

        messagesRepository.save(message);

    }

    public List<Message> getMessages() {

        return messagesRepository.findAll();

    }

}
