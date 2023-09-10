package ru.skypro.homework.mapping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.skypro.homework.dto.RoleDto.ADMIN;

/**
 * Class UserMappingServiceTest
 */
public class UserMappingServiceTest {


    private UserMappingService userMappingService;

    @BeforeEach
    void init() {
        userMappingService = new UserMappingService();

    }

    @Test
    void mapToEntity() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("zitarvita@mail.ru");
        registerDto.setPassword("00000000");
        registerDto.setFirstName("VITA");
        registerDto.setLastName("ZHITAR");
        registerDto.setPhone("+77777777777");
        registerDto.setRole(ADMIN);

        User user = userMappingService.mapToEntity(registerDto);

        assertEquals(registerDto.getUsername(), user.getUsername());
        assertEquals(registerDto.getPassword(), user.getPassword());
        assertEquals(registerDto.getFirstName(), user.getFirstName());
        assertEquals(registerDto.getLastName(), user.getLastName());
        assertEquals(registerDto.getPhone(), user.getPhone());
        assertEquals(registerDto.getRole(), user.getRole());
    }

    @Test
    void mapToDto() {
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        user.setFirstName("Name");
        user.setLastName("LastName");
        user.setPhone("+79990001122");
        user.setImage(null);

        UserDto userDto = userMappingService.mapToDto(user);

        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getPhone(), userDto.getPhone());
        assertEquals(user.getImage(), userDto.getImage());
    }
}
