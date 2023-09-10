package ru.skypro.homework.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.RoleDto;
import ru.skypro.homework.dto.TotalNumberAds;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exceptions.NotFoundEntityException;
import ru.skypro.homework.mapping.AdsMappingService;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.PictureService;

@ContextConfiguration(classes = {AdsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AdsServiceImplTest {
    @MockBean
    private AdsMappingService adsMappingService;

    @MockBean
    private AdsRepository adsRepository;

    @Autowired
    private AdsServiceImpl adsServiceImpl;

    @MockBean
    private PictureService pictureService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testAddAd() throws IOException {
        User user = new User();
        user.setAds(new ArrayList<>());
        user.setEmail("marie.gmail.com");
        user.setFirstName("Мария");
        user.setId(1);
        user.setImage("Изображение");
        user.setLastName("Селезнева");
        user.setPassword("12345Dao");
        user.setPhone("+79265853674");
        user.setRole(RoleDto.USER);
        user.setUsername("marusia");

        Ads ads = new Ads();
        ads.setComments(new ArrayList<>());
        ads.setId(1);
        ads.setImage("Изображение");
        ads.setName("Имя");
        ads.setPrice(1);
        ads.setTitle("Заголовок");
        ads.setUser(user);
        when(adsRepository.save((Ads) any())).thenReturn(ads);

        User user1 = new User();
        user1.setAds(new ArrayList<>());
        user1.setEmail("marie.gmail.com");
        user1.setFirstName("Мария");
        user1.setId(1);
        user1.setImage("Изображение");
        user1.setLastName("Селезнева");
        user1.setPassword("12345Dao");
        user1.setPhone("+79265853674");
        user1.setRole(RoleDto.USER);
        user1.setUsername("marusia");
        when(userRepository.findByUsername((String) any())).thenReturn(user1);

        User user2 = new User();
        user2.setAds(new ArrayList<>());
        user2.setEmail("marie.gmail.com");
        user2.setFirstName("Мария");
        user2.setId(1);
        user2.setImage("Изображение");
        user2.setLastName("Селезнева");
        user2.setPassword("12345Dao");
        user2.setPhone("+79265853674");
        user2.setRole(RoleDto.USER);
        user2.setUsername("marusia");

        Ads ads1 = new Ads();
        ads1.setComments(new ArrayList<>());
        ads1.setId(1);
        ads1.setImage("Изображение");
        ads1.setName("Имя");
        ads1.setPrice(1);
        ads1.setTitle("Заголовок");
        ads1.setUser(user2);
        AdsDto adsDto = new AdsDto();
        when(adsMappingService.mapToDto((Ads) any())).thenReturn(adsDto);
        when(adsMappingService.mapToEntity((CreateAdsDto) any(), (User) any())).thenReturn(ads1);
        when(pictureService.addImage((MultipartFile) any())).thenReturn("Добавить изображение");

        CreateAdsDto createAdsDto = new CreateAdsDto();
        createAdsDto.setDescription("Характеристики");
        createAdsDto.setPrice(1);
        createAdsDto.setTitle("Заголовок");
        assertSame(adsDto, adsServiceImpl.addAd(createAdsDto,
                new MockMultipartFile("Имя", new ByteArrayInputStream("ID".getBytes("UTF-8"))), "Сведения о пользователе"));
        verify(adsRepository).save((Ads) any());
        verify(userRepository).findByUsername((String) any());
        verify(adsMappingService).mapToDto((Ads) any());
        verify(adsMappingService).mapToEntity((CreateAdsDto) any(), (User) any());
        verify(pictureService).addImage((MultipartFile) any());
    }

    @Test
    void testAddAd2() throws IOException {
        User user = new User();
        user.setAds(new ArrayList<>());
        user.setEmail("marie.gmail.com");
        user.setFirstName("Мария");
        user.setId(1);
        user.setImage("Изображение");
        user.setLastName("Селезнева");
        user.setPassword("12345Dao");
        user.setPhone("+79265853674");
        user.setRole(RoleDto.USER);
        user.setUsername("marusia");

        Ads ads = new Ads();
        ads.setComments(new ArrayList<>());
        ads.setId(1);
        ads.setImage("Изображение");
        ads.setName("Имя");
        ads.setPrice(1);
        ads.setTitle("Заголовок");
        ads.setUser(user);
        when(adsRepository.save((Ads) any())).thenReturn(ads);

        User user1 = new User();
        user1.setAds(new ArrayList<>());
        user1.setEmail("marie.gmail.com");
        user1.setFirstName("Мария");
        user1.setId(1);
        user1.setImage("Изображение");
        user1.setLastName("Селезнева");
        user1.setPassword("12345Dao");
        user1.setPhone("+79265853674");
        user1.setRole(RoleDto.USER);
        user1.setUsername("marusia");
        when(userRepository.findByUsername((String) any())).thenReturn(user1);

        User user2 = new User();
        user2.setAds(new ArrayList<>());
        user2.setEmail("marie.gmail.com");
        user2.setFirstName("Мария");
        user2.setId(1);
        user2.setImage("Изображение");
        user2.setLastName("Селезнева");
        user2.setPassword("12345Dao");
        user2.setPhone("+79265853674");
        user2.setRole(RoleDto.USER);
        user2.setUsername("marusia");

        Ads ads1 = new Ads();
        ads1.setComments(new ArrayList<>());
        ads1.setId(1);
        ads1.setImage("Изображение");
        ads1.setName("Имя");
        ads1.setPrice(1);
        ads1.setTitle("Заголовок");
        ads1.setUser(user2);
        when(adsMappingService.mapToDto((Ads) any())).thenReturn(new AdsDto());
        when(adsMappingService.mapToEntity((CreateAdsDto) any(), (User) any())).thenReturn(ads1);
        when(pictureService.addImage((MultipartFile) any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));

        CreateAdsDto createAdsDto = new CreateAdsDto();
        createAdsDto.setDescription("Характеристики");
        createAdsDto.setPrice(1);
        createAdsDto.setTitle("Заголовок");
        assertThrows(ResponseStatusException.class, () -> adsServiceImpl.addAd(createAdsDto,
                new MockMultipartFile("Имя", new ByteArrayInputStream("ID".getBytes("UTF-8"))), "Сведения о пользователе"));
        verify(userRepository).findByUsername((String) any());
        verify(adsMappingService).mapToEntity((CreateAdsDto) any(), (User) any());
        verify(pictureService).addImage((MultipartFile) any());
    }

    @Test
    void testDeleteAdById() {
        User user = new User();
        user.setAds(new ArrayList<>());
        user.setEmail("marie.gmail.com");
        user.setFirstName("Мария");
        user.setId(1);
        user.setImage("Изображение");
        user.setLastName("Селезнева");
        user.setPassword("12345Dao");
        user.setPhone("+79265853674");
        user.setRole(RoleDto.USER);
        user.setUsername("marusia");

        Ads ads = new Ads();
        ads.setComments(new ArrayList<>());
        ads.setId(1);
        ads.setImage("Изобажение");
        ads.setName("Имя");
        ads.setPrice(1);
        ads.setTitle("Заголовок");
        ads.setUser(user);
        Optional<Ads> ofResult = Optional.of(ads);
        when(adsRepository.findById((Integer) any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setAds(new ArrayList<>());
        user1.setEmail("marie.gmail.com");
        user1.setFirstName("Мария");
        user1.setId(1);
        user1.setImage("Изображение");
        user1.setLastName("Селезнева");
        user1.setPassword("12345Dao");
        user1.setPhone("+79265853674");
        user1.setRole(RoleDto.USER);
        user1.setUsername("marusia");
        when(userRepository.findByUsername((String) any())).thenReturn(user1);
        assertThrows(ResponseStatusException.class, () -> adsServiceImpl.deleteAdById(1, "Сведения о пользователе"));
        verify(adsRepository).findById((Integer) any());
        verify(userRepository).findByUsername((String) any());
    }

    @Test
    void testDeleteAdById2() {
        User user = new User();
        user.setAds(new ArrayList<>());
        user.setEmail("marie.gmail.com");
        user.setFirstName("Мария");
        user.setId(1);
        user.setImage("Изображение");
        user.setLastName("Селезнева");
        user.setPassword("12345Dao");
        user.setPhone("+79265853674");
        user.setRole(RoleDto.USER);
        user.setUsername("marusia");

        Ads ads = new Ads();
        ads.setComments(new ArrayList<>());
        ads.setId(1);
        ads.setImage("Изображение");
        ads.setName("Имя");
        ads.setPrice(1);
        ads.setTitle("Заголовок");
        ads.setUser(user);
        Optional<Ads> ofResult = Optional.of(ads);
        when(adsRepository.findById((Integer) any())).thenReturn(ofResult);
        when(userRepository.findByUsername((String) any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        assertThrows(ResponseStatusException.class, () -> adsServiceImpl.deleteAdById(1, "Сведения о пользователе"));
        verify(userRepository).findByUsername((String) any());
    }

    @Test
    void testDeleteAdById3() {
        when(adsRepository.findById((Integer) any())).thenReturn(Optional.empty());

        User user = new User();
        user.setAds(new ArrayList<>());
        user.setEmail("marie.gmail.com");
        user.setFirstName("Мария");
        user.setId(1);
        user.setImage("Изображение");
        user.setLastName("Селезнева");
        user.setPassword("12345Dao");
        user.setPhone("+79265853674");
        user.setRole(RoleDto.USER);
        user.setUsername("marusia");
        User user1 = mock(User.class);
        when(user1.getUsername()).thenReturn("marusia");
        doNothing().when(user1).setAds((Collection<Ads>) any());
        doNothing().when(user1).setEmail((String) any());
        doNothing().when(user1).setFirstName((String) any());
        doNothing().when(user1).setId((Integer) any());
        doNothing().when(user1).setImage((String) any());
        doNothing().when(user1).setLastName((String) any());
        doNothing().when(user1).setPassword((String) any());
        doNothing().when(user1).setPhone((String) any());
        doNothing().when(user1).setRole((RoleDto) any());
        doNothing().when(user1).setUsername((String) any());
        user1.setAds(new ArrayList<>());
        user1.setEmail("marie.gmail.com");
        user1.setFirstName("Мария");
        user1.setId(1);
        user1.setImage("Изображение");
        user1.setLastName("Селезнева");
        user1.setPassword("12345Dao");
        user1.setPhone("+79265853674");
        user1.setRole(RoleDto.USER);
        user1.setUsername("marusia");
        Ads ads = mock(Ads.class);
        when(ads.getUser()).thenReturn(user1);
        doNothing().when(ads).setComments((Collection<Comment>) any());
        doNothing().when(ads).setId((Integer) any());
        doNothing().when(ads).setImage((String) any());
        doNothing().when(ads).setName((String) any());
        doNothing().when(ads).setPrice((Integer) any());
        doNothing().when(ads).setTitle((String) any());
        doNothing().when(ads).setUser((User) any());
        ads.setComments(new ArrayList<>());
        ads.setId(1);
        ads.setImage("Изображение");
        ads.setName("Имя");
        ads.setPrice(1);
        ads.setTitle("Заголовок");
        ads.setUser(user);

        User user2 = new User();
        user2.setAds(new ArrayList<>());
        user2.setEmail("marie.gmail.com");
        user2.setFirstName("Мария");
        user2.setId(1);
        user2.setImage("Изображение");
        user2.setLastName("Селезнева");
        user2.setPassword("12345Dao");
        user2.setPhone("+79265853674");
        user2.setRole(RoleDto.USER);
        user2.setUsername("marusia");
        when(userRepository.findByUsername((String) any())).thenReturn(user2);
        assertThrows(NotFoundEntityException.class, () -> adsServiceImpl.deleteAdById(1, "Сведения о пользователе"));
        verify(adsRepository).findById((Integer) any());
        verify(ads).setComments((Collection<Comment>) any());
        verify(ads).setId((Integer) any());
        verify(ads).setImage((String) any());
        verify(ads).setName((String) any());
        verify(ads).setPrice((Integer) any());
        verify(ads).setTitle((String) any());
        verify(ads).setUser((User) any());
        verify(user1).setAds((Collection<Ads>) any());
        verify(user1).setEmail((String) any());
        verify(user1).setFirstName((String) any());
        verify(user1).setId((Integer) any());
        verify(user1).setImage((String) any());
        verify(user1).setLastName((String) any());
        verify(user1).setPassword((String) any());
        verify(user1).setPhone((String) any());
        verify(user1).setRole((RoleDto) any());
        verify(user1).setUsername((String) any());
        verify(userRepository).findByUsername((String) any());
    }

    @Test
    void testDeleteAdById4() {
        User user = new User();
        user.setAds(new ArrayList<>());
        user.setEmail("marie.gmail.com");
        user.setFirstName("Мария");
        user.setId(1);
        user.setImage("Изображение");
        user.setLastName("Селезнева");
        user.setPassword("12345Dao");
        user.setPhone("+79265853674");
        user.setRole(RoleDto.USER);
        user.setUsername("marusia");
        User user1 = mock(User.class);
        when(user1.getUsername()).thenReturn("marusia");
        doNothing().when(user1).setAds((Collection<Ads>) any());
        doNothing().when(user1).setEmail((String) any());
        doNothing().when(user1).setFirstName((String) any());
        doNothing().when(user1).setId((Integer) any());
        doNothing().when(user1).setImage((String) any());
        doNothing().when(user1).setLastName((String) any());
        doNothing().when(user1).setPassword((String) any());
        doNothing().when(user1).setPhone((String) any());
        doNothing().when(user1).setRole((RoleDto) any());
        doNothing().when(user1).setUsername((String) any());
        user1.setAds(new ArrayList<>());
        user1.setEmail("marie.gmail.com");
        user1.setFirstName("Мария");
        user1.setId(1);
        user1.setImage("Изображение");
        user1.setLastName("Селезнева");
        user1.setPassword("12345Dao");
        user1.setPhone("+79265853674");
        user1.setRole(RoleDto.USER);
        user1.setUsername("marusia");
        Ads ads = mock(Ads.class);
        when(ads.getUser()).thenReturn(user1);
        doNothing().when(ads).setComments((Collection<Comment>) any());
        doNothing().when(ads).setId((Integer) any());
        doNothing().when(ads).setImage((String) any());
        doNothing().when(ads).setName((String) any());
        doNothing().when(ads).setPrice((Integer) any());
        doNothing().when(ads).setTitle((String) any());
        doNothing().when(ads).setUser((User) any());
        ads.setComments(new ArrayList<>());
        ads.setId(1);
        ads.setImage("Изображение");
        ads.setName("Имя");
        ads.setPrice(1);
        ads.setTitle("Заголовок");
        ads.setUser(user);
        Optional<Ads> ofResult = Optional.of(ads);
        doNothing().when(adsRepository).deleteById((Integer) any());
        when(adsRepository.findById((Integer) any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setAds(new ArrayList<>());
        user2.setEmail("marie.gmail.com");
        user2.setFirstName("Мария");
        user2.setId(1);
        user2.setImage("Изображение");
        user2.setLastName("Селезнева");
        user2.setPassword("12345Dao");
        user2.setPhone("+79265853674");
        user2.setRole(RoleDto.ADMIN);
        user2.setUsername("marusia");
        when(userRepository.findByUsername((String) any())).thenReturn(user2);
        assertTrue(adsServiceImpl.deleteAdById(1, "Сведения о пользователе"));
        verify(adsRepository).findById((Integer) any());
        verify(adsRepository).deleteById((Integer) any());
        verify(ads).getUser();
        verify(ads).setComments((Collection<Comment>) any());
        verify(ads).setId((Integer) any());
        verify(ads).setImage((String) any());
        verify(ads).setName((String) any());
        verify(ads).setPrice((Integer) any());
        verify(ads).setTitle((String) any());
        verify(ads).setUser((User) any());
        verify(user1).getUsername();
        verify(user1).setAds((Collection<Ads>) any());
        verify(user1).setEmail((String) any());
        verify(user1).setFirstName((String) any());
        verify(user1).setId((Integer) any());
        verify(user1).setImage((String) any());
        verify(user1).setLastName((String) any());
        verify(user1).setPassword((String) any());
        verify(user1).setPhone((String) any());
        verify(user1).setRole((RoleDto) any());
        verify(user1).setUsername((String) any());
        verify(userRepository).findByUsername((String) any());
    }

    @Test
    void testGetAdsMe() {
        when(adsRepository.findByUser((User) any())).thenReturn(new ArrayList<>());

        User user = new User();
        ArrayList<Ads> adsList = new ArrayList<>();
        user.setAds(adsList);
        user.setEmail("marie.gmail.com");
        user.setFirstName("Мария");
        user.setId(1);
        user.setImage("Изображение");
        user.setLastName("Селезнева");
        user.setPassword("12345Dao");
        user.setPhone("+79265853674");
        user.setRole(RoleDto.USER);
        user.setUsername("marusia");
        when(userRepository.findByUsername((String) any())).thenReturn(user);
        TotalNumberAds actualAdsMe = adsServiceImpl.getAdsMe("Сведения о пользователе");
        assertEquals(0, actualAdsMe.getCount().intValue());
        assertEquals(adsList, actualAdsMe.getResults());
        verify(adsRepository).findByUser((User) any());
        verify(userRepository).findByUsername((String) any());
    }

    @Test
    void testGetAdsMe2() {
        when(adsRepository.findByUser((User) any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));

        User user = new User();
        user.setAds(new ArrayList<>());
        user.setEmail("marie.gmail.com");
        user.setFirstName("Мария");
        user.setId(1);
        user.setImage("Изображение");
        user.setLastName("Селезнева");
        user.setPassword("12345Dao");
        user.setPhone("+79265853674");
        user.setRole(RoleDto.USER);
        user.setUsername("marusia");
        when(userRepository.findByUsername((String) any())).thenReturn(user);
        assertThrows(ResponseStatusException.class, () -> adsServiceImpl.getAdsMe("Сведения о пользователе"));
        verify(adsRepository).findByUser((User) any());
        verify(userRepository).findByUsername((String) any());
    }

    @Test
    void testGetAdsMe3() {
        User user = new User();
        user.setAds(new ArrayList<>());
        user.setEmail("marie.gmail.com");
        user.setFirstName("Мария");
        user.setId(1);
        user.setImage("Изображение");
        user.setLastName("Селезнева");
        user.setPassword("12345Dao");
        user.setPhone("+79265853674");
        user.setRole(RoleDto.USER);
        user.setUsername("marusia");

        Ads ads = new Ads();
        ads.setComments(new ArrayList<>());
        ads.setId(1);
        ads.setImage("Изображение");
        ads.setName("Имя");
        ads.setPrice(1);
        ads.setTitle("Заголовок");
        ads.setUser(user);

        ArrayList<Ads> adsList = new ArrayList<>();
        adsList.add(ads);
        when(adsRepository.findByUser((User) any())).thenReturn(adsList);

        User user1 = new User();
        user1.setAds(new ArrayList<>());
        user1.setEmail("marie.gmail.com");
        user1.setFirstName("Мария");
        user1.setId(1);
        user1.setImage("Изображение");
        user1.setLastName("Селезнева");
        user1.setPassword("12345Dao");
        user1.setPhone("+79265853674");
        user1.setRole(RoleDto.USER);
        user1.setUsername("marusia");
        when(userRepository.findByUsername((String) any())).thenReturn(user1);
        when(adsMappingService.mapToDto((Ads) any())).thenReturn(new AdsDto());
        TotalNumberAds actualAdsMe = adsServiceImpl.getAdsMe("Сведения о пользователе");
        assertEquals(1, actualAdsMe.getCount().intValue());
        assertEquals(1, actualAdsMe.getResults().size());
        verify(adsRepository).findByUser((User) any());
        verify(userRepository).findByUsername((String) any());
        verify(adsMappingService).mapToDto((Ads) any());
    }

    @Test
    void testGetAdsMe4() {
        User user = new User();
        user.setAds(new ArrayList<>());
        user.setEmail("marie.gmail.com");
        user.setFirstName("Мария");
        user.setId(1);
        user.setImage("Изображение");
        user.setLastName("Селезнева");
        user.setPassword("12345Dao");
        user.setPhone("+79265853674");
        user.setRole(RoleDto.USER);
        user.setUsername("marusia");

        Ads ads = new Ads();
        ads.setComments(new ArrayList<>());
        ads.setId(1);
        ads.setImage("Изображение");
        ads.setName("Имя");
        ads.setPrice(1);
        ads.setTitle("Заголовок");
        ads.setUser(user);

        ArrayList<Ads> adsList = new ArrayList<>();
        adsList.add(ads);
        when(adsRepository.findByUser((User) any())).thenReturn(adsList);

        User user1 = new User();
        user1.setAds(new ArrayList<>());
        user1.setEmail("marie.gmail.com");
        user1.setFirstName("Мария");
        user1.setId(1);
        user1.setImage("Image");
        user1.setLastName("Селезнева");
        user1.setPassword("12345Dao");
        user1.setPhone("+79265853674");
        user1.setRole(RoleDto.USER);
        user1.setUsername("marusia");
        when(userRepository.findByUsername((String) any())).thenReturn(user1);
        when(adsMappingService.mapToDto((Ads) any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        assertThrows(ResponseStatusException.class, () -> adsServiceImpl.getAdsMe("Сведения о пользователе"));
        verify(adsRepository).findByUser((User) any());
        verify(userRepository).findByUsername((String) any());
        verify(adsMappingService).mapToDto((Ads) any());
    }

    @Test
    void testFindByDescriptionAds() {
        ArrayList<Ads> adsList = new ArrayList<>();
        when(adsRepository.searchRorAnAdByName((String) any())).thenReturn(adsList);
        TotalNumberAds actualFindByDescriptionAdsResult = adsServiceImpl.findByDescriptionAds("Имя");
        assertEquals(0, actualFindByDescriptionAdsResult.getCount().intValue());
        assertEquals(adsList, actualFindByDescriptionAdsResult.getResults());
        verify(adsRepository).searchRorAnAdByName((String) any());
    }

    @Test
    @Disabled()
    void testFindByDescriptionAds2() {

        User user = new User();
        user.setAds(new ArrayList<>());
        user.setEmail("marie.gmail.com");
        user.setFirstName("Мария");
        user.setId(1);
        user.setImage("Image");
        user.setLastName("Селезнева");
        user.setPassword("12345Dao");
        user.setPhone("+79265853674");
        user.setRole(RoleDto.USER);
        user.setUsername("marusia");

        Ads ads = new Ads();
        ads.setComments(new ArrayList<>());
        ads.setId(1);
        ads.setImage("Изображение");
        ads.setName("Имя");
        ads.setPrice(1);
        ads.setTitle("Заголовок");
        ads.setUser(user);

        ArrayList<Ads> adsList = new ArrayList<>();
        adsList.add(ads);
        when(adsRepository.searchRorAnAdByName((String) any())).thenReturn(adsList);
        when(adsMappingService.mapToDto((Ads) any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        adsServiceImpl.findByDescriptionAds("Имя");
    }
}

