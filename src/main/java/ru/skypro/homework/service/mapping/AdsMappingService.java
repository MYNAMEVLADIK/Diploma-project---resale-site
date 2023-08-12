package ru.skypro.homework.service.mapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.User;

import java.util.ArrayList;
import java.util.List;

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

    public List<AdsDto> mapToListAdsDto(List<Ads> ads) {
        List<AdsDto> adsDtos = new ArrayList<>();

        if (ads != null && !ads.isEmpty()) {
            for (Ads ad : ads) {
                adsDtos.add(mapToDto(ad));
            }
        }

        return adsDtos;
    }

    public Ads mapCreatedAdsToEntity(CreateAdsDto dto) {
        if (dto == null) {
            return null;
        }

        Ads ad = new Ads();
        ad.setDescription(dto.getDescription());
        ad.setPrice(dto.getPrice());
        ad.setTitle(dto.getTitle());

        return ad;
    }

    public FullAdsDto mapToFullAdsDto(Ads ads) {
        if (ads == null) {
            return null;
        }

        FullAdsDto dto = new FullAdsDto();
        dto.setIdAds(ads.getId());
        dto.setAuthorFirstName(ads.getUser().getFirstName());
        dto.setAuthorLastName(ads.getUser().getLastName());
        dto.setDescription(ads.getDescription());
        dto.setEmail(ads.getUser().getEmail());
        dto.setImageAds(ads.getImage());
        dto.setPhone(ads.getUser().getPhone());
        dto.setPrice(ads.getPrice());
        dto.setTitle(ads.getTitle());

        return dto;
    }
}
