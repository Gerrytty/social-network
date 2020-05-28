package ru.itis.socialnetworkboot.service;

import org.springframework.stereotype.Service;
import ru.itis.socialnetworkboot.dto.MapDto;
import ru.itis.socialnetworkboot.model.Photo;
import ru.itis.socialnetworkboot.repository.interfaces.PhotosRepository;

import javax.transaction.Transactional;

@Service
public class LocationService {

    private final PhotosRepository photosRepository;

    public LocationService(PhotosRepository photosRepository) {
        this.photosRepository = photosRepository;
    }

    @Transactional
    public void setLocation(MapDto mapDto, Long photoId) {
        photosRepository.setLocation(Float.parseFloat(mapDto.getLat()), Float.parseFloat(mapDto.getLon()), photoId);
    }

    public MapDto getLocation(Long photoId) {
        Photo photo = photosRepository.findById(photoId).get();

        if(photo.getLatitude() != null) {
            return new MapDto(photo.getLatitude().toString(), photo.getLongitude().toString());
        }

        else {
            return new MapDto("no", "no");
        }

    }
}
