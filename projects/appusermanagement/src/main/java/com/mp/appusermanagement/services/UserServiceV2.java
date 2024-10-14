package com.mp.appusermanagement.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mp.appusermanagement.dto.UserDTOV2;
import com.mp.appusermanagement.models.UserModel;
import com.mp.appusermanagement.repositories.UserRepository;
import com.mp.appusermanagement.utils.Util;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;
    
    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> createUserV2(UserDTOV2 user) {
        if (!Util.isValidEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo electrónico no es válido.");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un usuario con este correo.");
        }
        if (!Util.isValidPassword(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña no cumple los criterios.");
        }
        if (userRepository.existsByNameAndLastName(user.getName(), user.getLastName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre y apellido ya existen en otro registro.");
        }

        UserModel newUser = convertDtoToEntityV2(user);
            
        try {
            userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario: " + e.getMessage());
        }
    }

    private UserModel convertDtoToEntityV2(UserDTOV2 dto) {
        return new UserModel(
            dto.getName(),
            dto.getLastName(),
            dto.getEmail(),
            dto.getPassword()
        );
    }
}
