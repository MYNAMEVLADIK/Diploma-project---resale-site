package ru.skypro.homework.mapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.User;

/**
 * Класс - маппинг объявлений
 */
@Service
@Slf4j
public class AdsMappingService {

    /**
     * Метод для маппинга сущности "Объявления" в дто
     */
    public AdsDto mapToDto(Ads entity) {

        AdsDto dto = new AdsDto();
        dto.setIdAd(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setPrice(entity.getPrice());
        dto.setImageAd(entity.getImage());
        dto.setAuthor(entity.getUser().getId());

        if (entity.getImage() != null) {
            dto.setImageAd(String.format("/ads/image/%s", entity.getImage()));
        } else {
            dto.setImageAd(null);
        }

        return dto;
    }

    /**
     * Метод для маппинга дто "Объявления" в сущность
     */
    public Ads mapToEntity(CreateAdsDto dto, User user) {

        Ads adsEntity = new Ads();
        adsEntity.setName(dto.getDescription());
        adsEntity.setTitle(dto.getTitle());
        adsEntity.setPrice(dto.getPrice());
        adsEntity.setUser(user);

        return adsEntity;
    }

    /**
     * Метод для ПОЛНОГО маппинга сущности "Объявления" в дто
     */
    public FullAdsDto mapToFullDto(Ads entity) {

        FullAdsDto dto = new FullAdsDto();
        dto.setDescription(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());
        dto.setEmail(entity.getUser().getUsername());
        dto.setAuthorFirstName(entity.getUser().getFirstName());
        dto.setAuthorLastName(entity.getUser().getLastName());
        dto.setIdAds(entity.getId());
        dto.setPhone(entity.getUser().getPhone());

        if (entity.getImage() != null) {
            dto.setImageAds(String.format("/ads/image/%s", entity.getImage()));
        } else {
            dto.setImageAds(null);
        }

        return dto;
    }
}
