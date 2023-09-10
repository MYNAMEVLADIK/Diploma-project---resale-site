package ru.skypro.homework.mapping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.skypro.homework.dto.RoleDto.ADMIN;

/**
 * Class AdsMappingServiceTest
 */
public class AdsMappingServiceTest {
    private AdsMappingService adsMappingService;
    private User user;
    private Ads ads;

    @BeforeEach
    void init () {
        adsMappingService = new AdsMappingService();
        user = new User();
        user.setFirstName("VIta");
        user.setLastName("zitar");
        user.setUsername("zitarvita@mail.com");
        user.setPhone("+77777777777");

        ads = new Ads();
        ads.setId(1);
        ads.setName("Подарок");
        ads.setPrice(990);
        ads.setTitle("Пирожные");
        ads.setImage(null);
        ads.setUser(user);
    }

    @Test
    void mapToDto() {

        AdsDto adsDto = adsMappingService.mapToDto(ads);

        assertEquals(adsDto.getIdAd(), ads.getId());
        assertEquals(adsDto.getTitle(), ads.getTitle());
        assertEquals(adsDto.getPrice(), ads.getPrice());
        assertEquals(adsDto.getImageAd(), ads.getImage());
    }

    @Test
    void mapToEntity() {

        CreateAdsDto createAdsDto = new CreateAdsDto();
        createAdsDto.setTitle("fghjk");
        createAdsDto.setDescription("fghjk");
        createAdsDto.setPrice(990);

        Ads ads1 = adsMappingService.mapToEntity(createAdsDto, user);

        assertEquals(createAdsDto.getTitle(), ads1.getName());
        assertEquals(createAdsDto.getDescription(), ads1.getTitle());
        assertEquals(createAdsDto.getPrice(), ads1.getPrice());
    }

    @Test
    void fullAdsDto() {

        FullAdsDto fullAdsDto = adsMappingService.mapToFullDto(ads);

        assertEquals(ads.getPrice(), fullAdsDto.getPrice());
        assertEquals(ads.getTitle(), fullAdsDto.getTitle());
        assertEquals(ads.getUser().getLastName(), fullAdsDto.getAuthorLastName());
        assertEquals(ads.getUser().getFirstName(), fullAdsDto.getAuthorFirstName());
        assertEquals(ads.getId(), fullAdsDto.getIdAds());
        assertEquals(ads.getUser().getPhone(), fullAdsDto.getPhone());

    }

}
