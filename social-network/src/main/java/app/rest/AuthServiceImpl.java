package ru.itis.socialnetworkboot.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.socialnetworkboot.dto.Mail;
import ru.itis.socialnetworkboot.dto.RegistrationDto;
import ru.itis.socialnetworkboot.model.User;
import ru.itis.socialnetworkboot.repository.interfaces.UsersRepository;
import ru.itis.socialnetworkboot.security.Role;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailServiceImpl mailService;
    private final TemplateForMailImpl templateForMail;

    public AuthServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder, MailServiceImpl mailService, TemplateForMailImpl templateForMail) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.templateForMail = templateForMail;
    }

    public User register(RegistrationDto dto) {

        User user = User.builder()
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .secondName(dto.getSecondName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.NOT_CONFIRMED)
                .build();

        try {

            usersRepository.save(user);

            User user1 = usersRepository.findByEmail(user.getEmail()).get();

            Map model = new HashMap<>();

            model.put("url", "localhost:8086/confirm?id=" + user1.getUserId());
            model.put("name", user1.getFirstName() + " " + user1.getSecondName());

            Mail mail = Mail.builder()
                    .to(user1.getEmail())
                    .from("gerrytty@yandex.ru")
                    .content("content")
                    .subject("Confirm registration")
                    .model(model).build();

            mailService.send(mail, templateForMail.getHtml(mail, "mail.ftlh"), true);
        }

        catch (Exception e) {

            e.printStackTrace();

            return null;

        }

        return user;
    }
}
