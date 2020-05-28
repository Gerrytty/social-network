package ru.itis.socialnetworkboot.service;

import org.springframework.stereotype.Service;
import ru.itis.socialnetworkboot.repository.interfaces.UsersRepository;

import javax.transaction.Transactional;

@Service
public class ConfirmServiceImpl {

    private final UsersRepository usersRepository;

    public ConfirmServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public void confirmRegistration(Long userId) {
        usersRepository.setConfirmFlag(userId);
    }
}
