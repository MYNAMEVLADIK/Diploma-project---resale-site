package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.mapping.AdsMappingService;
import ru.skypro.homework.service.mapping.CommentMappingService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final AdsMappingService adsMappingService;
    private final UserService userService;
    private final CommentMappingService commentMappingService;


    /**
     * Метод возвращает все объявления, которые есть в БД
     */
    @Override
    public TotalNumberAds getAllAds() {
        List<Ads> ads = adsRepository.findAll();

        if (ads.isEmpty()) {
            return new TotalNumberAds(0, new ArrayList<>());
        }

        List<AdsDto> adsDto = adsMappingService.mapToListAdsDto(ads);

        return new TotalNumberAds(adsDto.size(), adsDto);
    }

    @Override
    public AdsDto addAd(CreateAdsDto dto, MultipartFile image, String userDetails) {
        if (dto == null) {
            return null;
        }

        User user = userService.getAuthorizedUser();

        Ads ad = adsMappingService.mapCreatedAdsToEntity(dto);
        ad.setUser(user);
        adsRepository.save(ad);

        return adsMappingService.mapToDto(ad);
    }

    @Override
    public FullAdsDto getFullAdsById(Integer id) {
        if (!adsRepository.existsById(id)) {
            return null;
        }

        Ads ad = adsRepository.getReferenceById(id);

        return adsMappingService.mapToFullAdsDto(ad);
    }

    @Override
    public boolean deleteAdById(Integer id, String userDetails) {
        User currentUser = userService.getAuthorizedUser();
        Ads ad = adsRepository.getReferenceById(id);

        if (!ad.getUser().equals(currentUser)) {
            return false;
        }

        adsRepository.deleteById(id);
        return true;
    }

    @Override
    public AdsDto updateAdsById(Integer id, CreateAdsDto dto, String userDetails) {
        User currentUser = userService.getAuthorizedUser();
        Ads ad = adsRepository.getReferenceById(id);
        if (!currentUser.equals(ad.getUser())) {
            return null;
        }

        ad.setTitle(dto.getTitle());
        ad.setPrice(dto.getPrice());
        ad.setName(dto.getDescription());

        adsRepository.save(ad);

        return adsMappingService.mapToDto(ad);
    }

    /**
     * Метод возвращает все объявления указанного пользователя
     */
    @Override
    public TotalNumberAds getAdsMe(User user) {
        List<AdsDto> adsDtos = adsMappingService.mapToListAdsDto((List<Ads>) user.getAds());

        return new TotalNumberAds(adsDtos.size(), adsDtos);
    }

    @Override
    public TotalNumberAds findByDescriptionAds(String name) {
        List<AdsDto> dto = adsRepository
                .searchRorAnAdByName(name).stream()
                .map(adsMappingService::mapToDto)
                .collect(Collectors.toList());

        return new TotalNumberAds(dto.size(), dto);
    }

    @Override
    public boolean updateAdImage(Integer id, MultipartFile image) {
        return false;
    }

    @Override
    public TotalNumberComment getAllComments(Integer adId) {
        Ads ad = adsRepository.getReferenceById(adId);

        List<Comment> comments = (List<Comment>) ad.getComments();
        if (comments == null) {
            return new TotalNumberComment(0, new ArrayList<>());
        }

        return new TotalNumberComment(comments.size(), commentMappingService.mapToListDto(comments));
    }
}
