package com.mp.appusermanagement;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.mp.appusermanagement.dto.UserDTO;
import com.mp.appusermanagement.models.UserModel;
import com.mp.appusermanagement.repositories.UserRepository;
import com.mp.appusermanagement.services.UserService;

// TDD Endpoint
@SpringBootTest
class AppusermanagementApplicationTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserDTO userDTO;
    private UserModel userModel;

    @BeforeEach
    public void setUp() {
        userDTO = new UserDTO();
        userDTO.setName("Juan");
        userDTO.setLastName("Perez");
        userDTO.setEmail("juan.perez@example.com");
        userDTO.setPassword("Password@123");

        userModel = new UserModel();
        userModel.setName(userDTO.getName());
        userModel.setLastName(userDTO.getLastName());
        userModel.setEmail(userDTO.getEmail());
        userModel.setPassword(userDTO.getPassword());
    }

    @Test
    public void testCreateUser() {
        when(userRepository.save(any(UserModel.class))).thenReturn(userModel);

        ResponseEntity<Object> createdUser = userService.createUser(userDTO);

        Object body = createdUser.getBody();

        if (body instanceof UserModel) {
            UserModel user = (UserModel) body;

            assertNotNull(user);
            assertEquals(userDTO.getEmail(), user.getEmail());
        }
        
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userModel));

        UserModel foundUser = userService.getUserById(1L);

        assertNotNull(foundUser);
        assertEquals(userDTO.getName(), foundUser.getName());
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userModel));
        when(userRepository.save(any(UserModel.class))).thenReturn(userModel);

        UserModel updatedUser = userService.updateUser(1L, userDTO);

        assertNotNull(updatedUser);
        assertEquals(userDTO.getLastName(), updatedUser.getLastName());
    }

    @Test
    public void testDeleteUser() {
        when(userRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(userRepository).deleteById(anyLong());

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}
