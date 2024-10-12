package com.mp.appusermanagement.controllers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mp.appusermanagement.dto.UserDTO;
import com.mp.appusermanagement.dto.UserDTO2;
import com.mp.appusermanagement.models.UserModel;
import com.mp.appusermanagement.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")  // Habilitar CORS
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Crear un nuevo usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserDTO user) {
        return userService.createUser(user);
    }

    @Operation(summary = "Obtener un usuario por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@Parameter(description = "ID del usuario a recuperar") @PathVariable Long id) {
        try {
            UserModel user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }
    }

    @Operation(summary = "Actualizar un usuario existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@Parameter(description = "ID del usuario a actualizar") @PathVariable Long id, @RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @Operation(summary = "Eliminar un usuario por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@Parameter(description = "ID del usuario a eliminar") @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Crear un nuevo usuario v2.0")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping("/v2")
    public ResponseEntity<Object> createUserV2(@RequestBody @Valid UserDTO2 user) {
        return userService.createUserV2(user);
    }

}



