package com.mp.appusermanagement.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mp.appusermanagement.dto.UserDTO;
import com.mp.appusermanagement.models.UserModel;
import com.mp.appusermanagement.repositories.UserRepository;
import com.mp.appusermanagement.utils.Util;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Crear un nuevo usuario
    public ResponseEntity<Object> createUser(UserDTO user) {
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

        UserModel newUser = convertDtoToEntity(user);

        try {
            userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario: " + e.getMessage());
        }
    }

    // Obtener usuario por ID
    public ResponseEntity<Object> getUserById(Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }
    }
    
    
    

    // Actualizar usuario
    public ResponseEntity<Object> updateUser(Long id, UserDTO userDto) {
        Optional<UserModel> updateUser = userRepository.findById(id);

        if (updateUser.isPresent()) {
            UserModel user = updateUser.get();

            user.setName(userDto.getName());
            user.setLastName(userDto.getLastName());
            user.setPlaceBirth(userDto.getPlaceBirth());
            user.setAge(userDto.getAge());

            if (!Util.isValidEmail(userDto.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo electrónico no es válido.");
            }
            if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un usuario con este correo.");
            }
            if (!Util.isValidPassword(userDto.getPassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña no cumple los criterios.");
            }
            if (userRepository.existsByNameAndLastName(userDto.getName(), userDto.getLastName())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre y apellido ya existen en otro registro.");
            }
    
            try {
                userRepository.save(user);
                return ResponseEntity.ok(user);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar el usuario: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }

    }

    // Eliminar usuario por ID
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("Usuario no encontrado.");
        }
        
        userRepository.deleteById(id);
    }

    private UserModel convertDtoToEntity(UserDTO dto) {
        return new UserModel(
            dto.getName(),
            dto.getLastName(),
            dto.getEmail(),
            dto.getPassword(),
            dto.getPlaceBirth(),
            dto.getAge()
        );
    }
}