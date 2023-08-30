package ru.skypro.homework.mapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.RoleDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

/**
 * Класс - маппинг пользователей
 */
@Service
@Slf4j
public class UserMappingService {

    /**
     * Метод для маппинга дто "Пользователи" в сущность
     */
    public User mapToEntity(RegisterDto dto){

        User entity = new User();
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        entity.setRole((dto.getRole() == null) ? RoleDto.USER : dto.getRole());

        return entity;
    }

    /**
     * Метод для маппинга сущности "Пользователи" в дто
     */
    public UserDto mapToDto(User entity) {

        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());

        if (entity.getImage() != null) {
            dto.setImage(String.format("/users/image/%s", entity.getImage()));
        } else {
            dto.setImage(null);
        }

        return dto;
    }
}
