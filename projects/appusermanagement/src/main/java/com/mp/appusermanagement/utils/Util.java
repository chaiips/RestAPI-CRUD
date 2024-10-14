package com.mp.appusermanagement.utils;

public class Util {
    // Método para validar el formato del correo electrónico
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    // Validar contraseña
    public static boolean isValidPassword(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[a-z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[!@#$%*].*");
    }
}
