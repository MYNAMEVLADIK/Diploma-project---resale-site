package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.Principal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.PictureService;

@ContextConfiguration(classes = {AdsController.class})
@ExtendWith(SpringExtension.class)
class AdsControllerTest {
    @Autowired
    private AdsController adsController;

    @MockBean
    private AdsService adsService;

    @MockBean
    private PictureService pictureService;

    @Test
    void testAddAds() {
        AdsController adsController = null;
        CreateAdsDto properties = null;
        MultipartFile image = null;
        Principal principal = null;

        ResponseEntity<AdsDto> actualAddAdsResult = adsController.addAds(properties, image, principal);
    }

    @Test
    void testFindByDescriptionAds() throws Exception {
        Object[] uriVariables = new Object[]{};
        String[] values = new String[]{"foo"};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads/found_ads", uriVariables)
                .param("name", values);
        Object[] controllers = new Object[]{adsController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

    }

    @Test
    void testGetAds() throws Exception {
        Object[] uriVariables = new Object[]{1};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads/{id}", uriVariables);
        Object[] controllers = new Object[]{adsController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        ResultActions actualPerformResult = buildResult.perform(requestBuilder);
    }
    @Test
    void testGetAdsMe() throws Exception {
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads/me", uriVariables);
        Object[] controllers = new Object[]{adsController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        ResultActions actualPerformResult = buildResult.perform(requestBuilder);
    }
    @Test
    void testGetAllAds() throws Exception {
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads", uriVariables);
        Object[] controllers = new Object[]{adsController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        ResultActions actualPerformResult = buildResult.perform(requestBuilder);
    }

    @Test
    void testGetImage() throws Exception {
        Object[] uriVariables = new Object[]{"42"};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads/image/{id}", uriVariables);
        Object[] controllers = new Object[]{adsController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        ResultActions actualPerformResult = buildResult.perform(requestBuilder);
    }
    @Test
    void testRemoveAds() throws Exception {
        Object[] uriVariables = new Object[]{1};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/ads/{id}", uriVariables);
        Object[] controllers = new Object[]{adsController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        ResultActions actualPerformResult = buildResult.perform(requestBuilder);
    }
    @Test
    void testUpdateAds() throws Exception {
        Object[] uriVariables = new Object[]{1};
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.patch("/ads/{id}", uriVariables)
                .contentType(MediaType.APPLICATION_JSON);

        CreateAdsDto createAdsDto = new CreateAdsDto();
        createAdsDto.setDescription("Характеристики");
        createAdsDto.setPrice(1);
        createAdsDto.setTitle("Dr");

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(createAdsDto));
        Object[] controllers = new Object[]{adsController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        ResultActions actualPerformResult = buildResult.perform(requestBuilder);
    }
    @Test
    void testUpdateAdsImage() throws Exception {
        Object[] uriVariables = new Object[]{1};
        MockHttpServletRequestBuilder patchResult = MockMvcRequestBuilders.patch("/ads/{id}/image", uriVariables);
        String[] values = new String[]{String.valueOf((Object) null)};
        MockHttpServletRequestBuilder requestBuilder = patchResult.param("image", values);
        Object[] controllers = new Object[]{adsController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        ResultActions actualPerformResult = buildResult.perform(requestBuilder);
    }
}

