package app.service;

import app.dto.RegistrationDto;
import app.model.User;
import app.repository.interfaces.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(RegistrationDto dto) {

        User user = User.builder()
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .secondName(dto.getSecondName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        try {

            usersRepository.save(user);
        }

        catch (Exception e) {

            e.printStackTrace();

            return null;

        }

        return user;
    }
}
