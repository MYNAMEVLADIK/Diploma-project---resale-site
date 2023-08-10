package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.TotalNumberAds;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.mapping.AdsMappingService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final AdsMappingService adsMappingService;

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
        return null;
    }

    @Override
    public FullAdsDto getFullAdsById(Integer id) {
        return null;
    }

    @Override
    public boolean deleteAdById(Integer id, String userDetails) {
        return false;
    }

    @Override
    public AdsDto updateAdsById(Integer id, CreateAdsDto dto, String userDetails) {
        return null;
    }

    /**
     * Метод возвращает все объявления указанного пользователя
     * @param user - пользователь
     */
    @Override
    public TotalNumberAds getAdsMe(User user) {
        List<AdsDto> adsDtos = adsMappingService.mapToListAdsDto((List<Ads>) user.getAds());

        return new TotalNumberAds(adsDtos.size(), adsDtos);
    }

    @Override
    public TotalNumberAds findByDescriptionAd(String description) {
        return null;
    }

    @Override
    public boolean updateAdImage(Integer id, MultipartFile image) {
        return false;
    }
}
