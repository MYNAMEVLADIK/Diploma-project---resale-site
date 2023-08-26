package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exceptions.NotFoundEntityException;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.PictureService;
import ru.skypro.homework.service.mapping.AdsMappingService;
import ru.skypro.homework.service.mapping.FullAdsMappingService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final UserRepository userRepository;
    private final AdsMappingService adsMapping;
    private final FullAdsMappingService fullAdsMapping;
    private final PictureService pictureService;

    @Override
    public TotalNumberAds getAllAds() {

        List<AdsDto> adsDto = adsRepository.findAll().stream()
                .map(adsMapping::mapToDto)
                .collect(Collectors.toList());

        return new TotalNumberAds(adsDto.size(), adsDto);
    }

    @Override
    public AdsDto addAd(CreateAdsDto dto, MultipartFile image, String userDetails) {


        User user = userRepository.findByUsername(userDetails);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        Ads entity = adsMapping.mapToEntity(dto, user);
        String imageId = pictureService.addImage(image);
        entity.setImage(imageId);

        adsRepository.save(entity);

        AdsDto adsDto = adsMapping.mapToDto(entity);

        return adsDto;
    }

    @Override
    public FullAdsDto getFullAdsById(Integer id) {

        Ads entity = adsRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Сущность не найдена"));

        FullAdsDto dto = fullAdsMapping.mapToDto(entity);

        return dto;
    }

    @Override
    public boolean deleteAdById(Integer id, String userDetails) {

        User authorOrAdmin = userRepository.findByUsername(userDetails);
        Ads entity = adsRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Сущность не найдена"));

        if (entity.getUser().getUsername().equals(userDetails)
                || authorOrAdmin.getRole() == RoleDto.ADMIN) {

            adsRepository.deleteById(id);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    @Transactional
    public AdsDto updateAdsById(Integer id, CreateAdsDto dto, String userDetails) {

        User authorOrAdmin = userRepository.findByUsername(userDetails);
        Ads entity = adsRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Сущность не найдена"));

        if (entity.getUser().getUsername().equals(userDetails)
                || authorOrAdmin.getRole() == (RoleDto.ADMIN)) {

            entity.setName(dto.getDescription());
            entity.setPrice(dto.getPrice());
            entity.setTitle(dto.getTitle());

            adsRepository.save(entity);

            AdsDto adsDto = adsMapping.mapToDto(entity);

            return adsDto;

        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public TotalNumberAds getAdsMe(String userDetails) {

        User author = userRepository.findByUsername(userDetails);
        if (author != null) {
            List<Ads> adEntity = adsRepository.findByUser(author);
            List<AdsDto> dto = new ArrayList<>();

            for (Ads ads : adEntity) {
                dto.add(adsMapping.mapToDto(ads));
            }
            return new TotalNumberAds(dto.size(), dto);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public TotalNumberAds findByDescriptionAds(String name) {

        List<AdsDto> dto = adsRepository
                .searchRorAnAdByName(name).stream()
                .map(adsMapping::mapToDto)
                .collect(Collectors.toList());

        return new TotalNumberAds(dto.size(), dto);
    }

    @Override
    public boolean updateAdImage(Integer id, MultipartFile image) {

        String imageId = pictureService.addImage(image);
        Ads entity = adsRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Сущность не найдена"));

        entity.setImage(imageId);
        adsRepository.save(entity);

        return true;
    }
}
