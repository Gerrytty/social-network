package app.service;

import app.model.User;
import app.repository.interfaces.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl {

    @Autowired
    UsersRepository usersRepository;

    public List<User> allUsers() {

        return usersRepository.findAll();

    }

}
