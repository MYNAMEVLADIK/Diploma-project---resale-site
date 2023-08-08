package ru.skypro.homework.service.mapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.User;

@Service
@Slf4j
public class AdsMappingService {

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

    public Ads mapToEntity(CreateAdsDto dto, User user) {

        Ads adsEntity = new Ads();
        adsEntity.setDescription(dto.getDescription());
        adsEntity.setTitle(dto.getTitle());
        adsEntity.setPrice(dto.getPrice());
        adsEntity.setUser(user);

        return adsEntity;
    }
}
