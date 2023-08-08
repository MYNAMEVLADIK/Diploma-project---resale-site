package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.CreateCommentDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.service.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean validate(Object object) {

        if (object instanceof CreateAdsDto) {
            return ((CreateAdsDto) object).getDescription() != null
                    && ((CreateAdsDto) object).getTitle() != null
                    && ((CreateAdsDto) object).getPrice() != 0;

        } else if (object instanceof RegisterDto) {
            return ((RegisterDto) object).getUsername() != null
                    && ((RegisterDto) object).getPassword() != null
                    && ((RegisterDto) object).getFirstName() != null
                    && ((RegisterDto) object).getLastName() != null
                    && ((RegisterDto) object).getPhone() != null;

        } else if (object instanceof CreateCommentDto) {
            return ((CreateCommentDto) object).getText() != null;
        }

        return false;
    }
}
