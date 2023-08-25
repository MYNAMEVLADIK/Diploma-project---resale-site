package ru.skypro.homework.service.mapping;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ads;
@Service
public class FullAdsMappingService {
    public FullAdsDto mapToDto(Ads entity) {

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
