package ru.skypro.homework.service.mapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.RoleDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

@Service
@Slf4j
public class UserMappingService {

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
