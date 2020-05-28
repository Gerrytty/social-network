package ru.itis.socialnetworkboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.socialnetworkboot.model.User;
import ru.itis.socialnetworkboot.repository.interfaces.UsersRepository;

import javax.transaction.Transactional;

@Service
public class AvaServiceImpl {

    private final UsersRepository usersRepository;

    @Autowired
    public AvaServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public void setAva(String path, Long userId, User user) {
        usersRepository.updatePathToAvaByUserId(path, userId);
        user.setPathToAva(path);
    }
}
