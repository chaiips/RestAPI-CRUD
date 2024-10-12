package com.mp.appusermanagement.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mp.appusermanagement.dto.UserDTO;
import com.mp.appusermanagement.dto.UserDTO2;
import com.mp.appusermanagement.models.UserModel;
import com.mp.appusermanagement.repositories.UserRepository;
import com.mp.appusermanagement.utils.Util;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Crear un nuevo usuario
    public ResponseEntity<Object> createUser(UserDTO user) {
        Util util = new Util();

        if (!util.isValidEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo electrónico no es válido.");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un usuario con este correo.");
        }
        if (!util.validatePassword(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña no cumple los criterios.");
        }
        if (userRepository.existsByNameAndLastName(user.getName(), user.getLastName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre y apellido ya existen en otro registro.");
        }

        UserModel newUser = new UserModel();
        newUser.setName(user.getName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setPlaceBirth(user.getPlaceBirth());
        newUser.setAge(user.getAge());    

        try {
            // Guardar el usuario en la base de datos
            userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con éxito.");
        } catch (Exception e) {
            // Manejar la excepción y retornar un mensaje de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario: " + e.getMessage());
        }
    }

    // Obtener usuario por ID
    public UserModel getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado."));
    }

    // Actualizar usuario
    public UserModel updateUser(Long id, UserDTO user) {
        UserModel updateUser = getUserById(id);
        updateUser.setName(user.getName());
        updateUser.setLastName(user.getLastName());
        updateUser.setPlaceBirth(user.getLastName());
        updateUser.setAge(user.getAge());
        return userRepository.save(updateUser);
    }

    // Eliminar usuario por ID
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("Usuario no encontrado.");
        }
        
        userRepository.deleteById(id);
    }

    // Crear un nuevo usuario v2 - Logica interna de Version 2.0
     public ResponseEntity<Object> createUserV2(UserDTO2 user) {
        Util util = new Util();

        if (!util.isValidEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo electrónico no es válido.");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un usuario con este correo.");
        }
        if (!util.validatePassword(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña no cumple los criterios.");
        }
        if (userRepository.existsByNameAndLastName(user.getName(), user.getLastName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre y apellido ya existen en otro registro.");
        }

        UserModel newUser = new UserModel();
        newUser.setName(user.getName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
            
        try {
            // Guardar el usuario en la base de datos
            userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con éxito.");
        } catch (Exception e) {
            // Manejar la excepción y retornar un mensaje de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario: " + e.getMessage());
        }
    }
}

