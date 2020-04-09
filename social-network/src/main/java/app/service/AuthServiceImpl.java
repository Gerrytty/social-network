package app.service;

import app.dto.AuthenticationDto;
import app.dto.RegistrationDto;
import app.model.User;
import app.repository.interfaces.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.rmi.NotBoundException;
import java.util.Optional;

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

    public User auth(AuthenticationDto dto) throws NotBoundException {

        Optional<User> user = usersRepository.findByEmail(dto.getEmail());

        if(!user.isPresent()) {
            throw new NotBoundException();
        }

        if(!passwordEncoder.matches(dto.getPassword(), user.get().getPassword())) {
            throw new NotBoundException();
        }

        return user.get();

    }

}
