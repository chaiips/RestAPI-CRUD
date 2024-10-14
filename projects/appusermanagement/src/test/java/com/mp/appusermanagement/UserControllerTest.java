package com.mp.appusermanagement;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mp.appusermanagement.controllers.UserController;
import com.mp.appusermanagement.dto.UserDTO;
import com.mp.appusermanagement.models.UserModel;
import com.mp.appusermanagement.repositories.UserRepository;
import com.mp.appusermanagement.services.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // :::::::::::::::::::::::::::::::::::::::::::::::             :::::::::::::::::::::::::::::::::::::::::::::::
    // ::::::::::::::::::::::::::::::::::::::::::::::: CREATE USER :::::::::::::::::::::::::::::::::::::::::::::::
    // :::::::::::::::::::::::::::::::::::::::::::::::             :::::::::::::::::::::::::::::::::::::::::::::::
    @Test
    public void createUser() throws Exception {
        UserDTO userDTO = new UserDTO("John", "Doe", "Jose@gmail.com", "asdf12*2A", "EDO MEX", 30);

        when(userService.createUser(any(UserDTO.class)))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente"));

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Usuario creado exitosamente"));
    }

    /* 
    @Test
    public void createUser_ReturnsBadRequestStatus_WhenEmailIsInvalid() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("invalid-email"); // Correo electrónico inválido
        userDTO.setName("Chay");
        userDTO.setLastName("Casti");
        userDTO.setPassword("ValidPass*word123");

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("El correo electrónico no es válido.")); // Verificar el mensaje de error
    }

    @Test
    public void createUser_ReturnsBadRequestStatus_WhenUserAlreadyExists() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("existinguser@example.com"); // Correo que ya existe en el repositorio
        userDTO.setName("Existing");
        userDTO.setLastName("User");
        userDTO.setPassword("ValidPassword123");

        // Mockear el comportamiento del repositorio
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(new UserModel()));

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ya existe un usuario con este correo.")); // Verificar el mensaje de error
    }
    */
    // :::::::::::::::::::::::::::::::::::::::::::::::             :::::::::::::::::::::::::::::::::::::::::::::::
    // ::::::::::::::::::::::::::::::::::::::::::::: FIND BY ID USER :::::::::::::::::::::::::::::::::::::::::::::
    // :::::::::::::::::::::::::::::::::::::::::::::::             :::::::::::::::::::::::::::::::::::::::::::::::
    // 200 Ok
    @Test
    public void getUserById_ReturnsUser_WhenUserExists() throws Exception {
        UserModel userModel = new UserModel("Jose", "Castillo", "Doe@gmail.com", "123456asdf*");

        // Configura el comportamiento del servicio
        when(userService.getUserById(1L)).thenReturn(ResponseEntity.ok(userModel));

        // Realiza la llamada al endpoint y verifica los resultados
        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jose")) // Cambia "John" a "Jose"
                .andExpect(jsonPath("$.lastName").value("Castillo"))
                .andExpect(jsonPath("$.email").value("Doe@gmail.com"));
    }

    /* 
    // 404 NOT FOUND
    @Test
    public void getUserById_ReturnsNotFound_WhenUserDoesNotExist() throws Exception {
        when(userService.getUserById(100L)).thenThrow(new NoSuchElementException("Usuario no encontrado"));

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Usuario no encontrado."));
    }
                */

    // :::::::::::::::::::::::::::::::::::::::::::::::             :::::::::::::::::::::::::::::::::::::::::::::::
    // :::::::::::::::::::::::::::::::::::::::::::::: UPDATE USER  :::::::::::::::::::::::::::::::::::::::::::::::
    // :::::::::::::::::::::::::::::::::::::::::::::::             :::::::::::::::::::::::::::::::::::::::::::::::
    // 200 OK
    @Test
    public void updateUser_ReturnsUpdatedUser_WhenSuccessful() throws Exception {
        UserDTO userDTO = new UserDTO("Jose", "Chayane", "jose@example.com", "654sd654*", "Mexico", 25);
        UserModel updatedUser = new UserModel("Jane", "Contreas", "jane@example.com", "654sd654");

        when(userService.updateUser(eq(1L), any(UserDTO.class))).thenReturn(ResponseEntity.ok(updatedUser));

        mockMvc.perform(put("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane"));
    }

    // 404 NOT FOUND
    @Test
    public void updateUser_ReturnsNotFound_WhenUserDoesNotExist() throws Exception {
        UserDTO userDTO = new UserDTO("Jose", "Castillo", "jose@example.com", "654sd654*", "CDMX", 25);

        when(userService.updateUser(eq(1L), any(UserDTO.class)))
                .thenThrow(new NoSuchElementException("Usuario no encontrado"));

        mockMvc.perform(put("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Usuario no encontrado."));
    }

    // :::::::::::::::::::::::::::::::::::::::::::::::             :::::::::::::::::::::::::::::::::::::::::::::::
    // ::::::::::::::::::::::::::::::::::::::::::::::: DELETE USER :::::::::::::::::::::::::::::::::::::::::::::::
    // :::::::::::::::::::::::::::::::::::::::::::::::             :::::::::::::::::::::::::::::::::::::::::::::::
    // 204 NO CONTENT
    @Test
    public void deleteUser_ReturnsNoContent_WhenSuccessful() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isNoContent());
    }

    /*
    // 404 NOT FOUND
    @Test
    public void deleteUser_ReturnsNotFound_WhenUserDoesNotExist() throws Exception {
        doThrow(new NoSuchElementException("Usuario no encontrado")).when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Usuario no encontrado."));
    } 
                */
}
