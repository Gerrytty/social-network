package app.controller;

import app.dto.MessageDto;
import app.service.MessagesServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MessagesController {

    private static final Map<String, List<MessageDto>> messages = new HashMap<>();

    private static final Map<Chat, List<MessageDto>> map = new HashMap<>();

    @Autowired
    MessagesServiceImpl messagesService;

    // получили сообщение от какой либо страницы - мы его разошлем во все другие страницы
    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageDto message) {

        System.out.println(message);

        if(!message.getText().equals("Login")) {
            messagesService.addMessage(message);
        }

        String text = message.getText();
        String senderId = message.getSenderId();
        String reciverId = message.getReciverId();

        System.out.println(text);
        System.out.println(senderId);
        System.out.println(reciverId);


        // если сообщений с этой или для этой страницы еще не было
        if (!messages.containsKey(message.getPageId())) {
            // добавляем эту страницу в Map-у страниц
            messages.put(message.getPageId(), new ArrayList<>());
        }
        // полученное сообщение добавляем для всех открытых страниц нашего приложения
        for (List<MessageDto> pageMessages : messages.values()) {
            // перед тем как положить сообщение для какой-либо страницы
            // мы список сообщений блокируем
            synchronized (pageMessages) {
                // добавляем сообщение
                pageMessages.add(message);
                // говорим, что другие потоки могут воспользоваться этим списком
                pageMessages.notifyAll();
            }
        }

        return ResponseEntity.ok().build();
    }

    // получить все сообщения для текущего запроса
    @SneakyThrows
    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ResponseEntity<List<MessageDto>> getMessagesForPage(@RequestParam Long reciverId) {

        System.out.println("Get method + reciver id = " + reciverId);

//        // получили список сообшений для страницы и заблокировали его
//        synchronized (messages.get(pageId)) {
//            // если нет сообщений уходим в ожидание
//            if (messages.get(pageId).isEmpty()) {
//                messages.get(pageId).wait();
//            }
//        }
//
//        // если сообщения есть - то кладем их в новых список
//        List<MessageDto> response = new ArrayList<>(messages.get(pageId));
//        // удаляем сообщения из мапы
//        messages.get(pageId).clear();
//        return ResponseEntity.ok(response);

        return ResponseEntity.ok(new ArrayList<>());
    }
}