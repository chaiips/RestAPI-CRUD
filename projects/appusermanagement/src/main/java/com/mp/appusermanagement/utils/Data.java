package com.mp.appusermanagement.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mp.appusermanagement.models.UserModel;
import com.mp.appusermanagement.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class Data {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        // Crear usuarios iniciales
        UserModel user1 = new UserModel();
        user1.setName("Juan");
        user1.setLastName("Pérez");
        user1.setEmail("juan.perez@example.com");
        user1.setPassword("password123");
        user1.setPlaceBirth("Mexico");
        user1.setAge(30);
        userRepository.save(user1);

        UserModel user2 = new UserModel();
        user2.setName("Ana");
        user2.setLastName("García");
        user2.setEmail("ana.garcia@example.com");
        user2.setPassword("password456");
        user2.setPlaceBirth("Mexico");
        user2.setAge(25);
        userRepository.save(user2);

        UserModel user3 = new UserModel();
        user3.setName("Carlos");
        user3.setLastName("López");
        user3.setEmail("carlos.lopez@example.com");
        user3.setPassword("password789");
        user3.setPlaceBirth("Mexico");
        user3.setAge(28);
        userRepository.save(user3);

        UserModel user4 = new UserModel();
        user4.setName("Luisa");
        user4.setLastName("Fernández");
        user4.setEmail("luisa.fernandez@example.com");
        user4.setPassword("password321");
        user4.setPlaceBirth("Mexico");
        user4.setAge(22);
        userRepository.save(user4);

        UserModel user5 = new UserModel();
        user5.setName("Pedro");
        user5.setLastName("Martínez");
        user5.setEmail("pedro.martinez@example.com");
        user5.setPassword("password654");
        user5.setPlaceBirth("Mexico");
        user5.setAge(35);
        userRepository.save(user5);

        UserModel user6 = new UserModel();
        user6.setName("María");
        user6.setLastName("Sánchez");
        user6.setEmail("maria.sanchez@example.com");
        user6.setPassword("password987");
        user6.setPlaceBirth("Mexico");
        user6.setAge(29);
        userRepository.save(user6);

        UserModel user7 = new UserModel();
        user7.setName("Jorge");
        user7.setLastName("Ramírez");
        user7.setEmail("jorge.ramirez@example.com");
        user7.setPassword("password135");
        user7.setPlaceBirth("Mexico");
        user7.setAge(32);
        userRepository.save(user7);

        UserModel user8 = new UserModel();
        user8.setName("Isabel");
        user8.setLastName("Torres");
        user8.setEmail("isabel.torres@example.com");
        user8.setPassword("password246");
        user8.setPlaceBirth("Mexico");
        user8.setAge(27);
        userRepository.save(user8);

        UserModel user9 = new UserModel();
        user9.setName("Fernando");
        user9.setLastName("Vásquez");
        user9.setEmail("fernando.vasquez@example.com");
        user9.setPassword("password369");
        user9.setPlaceBirth("Mexico");
        user9.setAge(40);
        userRepository.save(user9);

        UserModel user10 = new UserModel();
        user10.setName("Carmen");
        user10.setLastName("Moreno");
        user10.setEmail("carmen.moreno@example.com");
        user10.setPassword("password147");
        user10.setPlaceBirth("Mexico");
        user10.setAge(31);
        userRepository.save(user10);

        UserModel user11 = new UserModel();
        user11.setName("Laura");
        user11.setLastName("Gutiérrez");
        user11.setEmail("laura.gutierrez@example.com");
        user11.setPassword("password258");
        user11.setPlaceBirth("Mexico");
        user11.setAge(26);
        userRepository.save(user11);

        UserModel user12 = new UserModel();
        user12.setName("Ricardo");
        user12.setLastName("Díaz");
        user12.setEmail("ricardo.diaz@example.com");
        user12.setPassword("password369");
        user12.setPlaceBirth("Mexico");
        user12.setAge(33);
        userRepository.save(user12);

        UserModel user13 = new UserModel();
        user13.setName("Elena");
        user13.setLastName("Castro");
        user13.setEmail("elena.castro@example.com");
        user13.setPassword("password159");
        user13.setPlaceBirth("Mexico");
        user13.setAge(24);
        userRepository.save(user13);

        UserModel user14 = new UserModel();
        user14.setName("Alberto");
        user14.setLastName("Reyes");
        user14.setEmail("alberto.reyes@example.com");
        user14.setPassword("password753");
        user14.setPlaceBirth("Mexico");
        user14.setAge(36);
        userRepository.save(user14);

        UserModel user15 = new UserModel();
        user15.setName("Patricia");
        user15.setLastName("Cruz");
        user15.setEmail("patricia.cruz@example.com");
        user15.setPassword("password951");
        user15.setPlaceBirth("Mexico");
        user15.setAge(29);
        userRepository.save(user15);
    }
}
