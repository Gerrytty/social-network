package ru.itis.socialnetworkboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.socialnetworkboot.dto.PhotoDto;
import ru.itis.socialnetworkboot.dto.UploadForm;
import ru.itis.socialnetworkboot.model.Album;
import ru.itis.socialnetworkboot.model.Photo;
import ru.itis.socialnetworkboot.model.User;
import ru.itis.socialnetworkboot.repository.interfaces.PhotosRepository;

import java.util.List;

@Service
public class PhotoService {

    private final PhotosRepository photosRepository;

    @Autowired
    public PhotoService(PhotosRepository photosRepository) {
        this.photosRepository = photosRepository;
    }

    public void setPhoto(UploadForm uploadForm, String url) {
        Photo photo = Photo.builder()
                .album(new Album(uploadForm.getAlbumId()))
                .owner(new User(uploadForm.getUserId()))
                .url(url)
                .build();

        photosRepository.save(photo);

    }

    public List<PhotoDto> getAlbumsPhotos(Long albumId) {

        return PhotoDto.from(photosRepository.findAllByAlbum(new Album(albumId)));
    }

}
