package com.compass.thiagofv.config;

import com.compass.thiagofv.domain.Permission;
import com.compass.thiagofv.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public void run(String... args) throws Exception {
        if (permissionRepository.count() == 0) {
            Permission adminPermission = new Permission();
            adminPermission.setDescription("ADMIN");

            Permission userPermission = new Permission();
            userPermission.setDescription("USUARIO");

            permissionRepository.save(adminPermission);
            permissionRepository.save(userPermission);
        }
    }
}
