package app.service;

import app.dto.ProfileInfoDto;
import app.model.User;
import app.repository.interfaces.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NotBoundException;

@Service
public class ProfileServiceImpl {

    @Autowired
    private UsersRepository usersRepository;

    public ProfileInfoDto getProfileInfo(User user) throws NotBoundException {

        return ProfileInfoDto.builder()
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .town(user.getTown())
                .birthDate(user.getBirthDate())
                .build();

    }

}
