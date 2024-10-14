package com.mp.appusermanagement.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mp.appusermanagement.dto.UserDTOV2;
import com.mp.appusermanagement.services.UserServiceV2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v2/users")
public class UserControllerV2 {

    private final UserServiceV2 userService;

    public UserControllerV2(UserServiceV2 userService) {
        this.userService = userService;
    }

    @Operation(summary = "Crear un nuevo usuario v2.0")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<Object> createUserV2(@RequestBody @Valid UserDTOV2 user) {
        return userService.createUserV2(user);
    }
}
