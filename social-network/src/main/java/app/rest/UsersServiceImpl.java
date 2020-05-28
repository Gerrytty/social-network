package ru.itis.socialnetworkboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.socialnetworkboot.model.User;
import ru.itis.socialnetworkboot.repository.interfaces.UsersRepository;

import java.util.List;

@Service
public class UsersServiceImpl {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> allUsers() {
        return usersRepository.findAll();
    }

    public List<User> getAllByFirstNameAndSecondName(String name) {
        String[] strings = name.split(" ");

        if(strings.length == 2) {
            return usersRepository.findAllByFirstNameAndSecondName(strings[0], strings[1]);
        }

        else {
            List<User> users = usersRepository.findAllByFirstName(name);
            if(users.size() == 0) {
                return usersRepository.findAllBySecondName(name);
            }

            else return users;
        }
    }
}
