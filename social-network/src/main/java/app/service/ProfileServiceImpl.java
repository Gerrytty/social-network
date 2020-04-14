package app.service;

import app.dto.ProfileInfoDto;
import app.model.User;
import org.springframework.stereotype.Service;

import java.rmi.NotBoundException;

@Service
public class ProfileServiceImpl {

    public ProfileInfoDto getProfileInfo(User user) {

        return ProfileInfoDto.builder()
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .town(user.getTown())
                .birthDate(user.getBirthDate())
                .pathToAva(user.getPathToAva())
                .build();

    }

}
