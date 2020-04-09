package app.service;

import app.dto.ProfileInfoDto;
import app.model.User;
import app.repository.interfaces.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NotBoundException;
import java.util.Optional;

@Service
public class ProfileServiceImpl {

    @Autowired
    private UsersRepository usersRepository;

    public ProfileInfoDto getProfileInfo(Long id) throws NotBoundException {

        Optional<User> user = usersRepository.find(id);

        if(!user.isPresent()) {
            throw new NotBoundException();
        }

        else return ProfileInfoDto.builder()
                .firstName(user.get().getFirstName())
                .secondName(user.get().getSecondName())
                .town(user.get().getTown())
                .birthDate(user.get().getBirthDate())
                .build();

    }

}
