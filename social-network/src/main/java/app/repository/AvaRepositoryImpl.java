package app.repository;

import app.dto.UploadDto;
import app.repository.interfaces.AvaRepository;

import java.util.List;
import java.util.Optional;

public class AvaRepositoryImpl implements AvaRepository {

    @Override
    public Optional<UploadDto> find(Long id) {
        return Optional.empty();
    }

    @Override
    public List<UploadDto> findAll() {
        return null;
    }

    @Override
    public void save(UploadDto entity) {

    }

    @Override
    public void delete(Long aLong) {

    }


}
