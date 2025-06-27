package com.parkingPg.parkingPg;


import com.parkingPg.parkingPg.entity.Role;
import com.parkingPg.parkingPg.entity.User;
import com.parkingPg.parkingPg.repository.RoleRepository;
import com.parkingPg.parkingPg.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootTest(classes = ParkingPgApplication.class)
public class UserTest {

    private Logger logger = LoggerFactory.getLogger(UserTest.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Test
    public void createUser() {
        User user = new User();
        user.setFirstName("Admin");
        user.setLastName("Admin");
        user.setPassword("admin");
        userRepository.save(user);
        logger.info("User created: {}", user.getFirstName());
    }

    @Test
    public void createRole() {
        Role role = new Role();
        role.setName("admin");
        role.setDescription("Admin duty");
        roleRepository.save(role);
        logger.info("Role created: {}", role.getName());
    }
    @Test
    public void assignRoleToUser() {
        Role role = roleRepository.findByid(1);
        Optional<User> user = userRepository.findById(1);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.get().setRoleSet(roleSet);
        userRepository.save(user.get());
        logger.info("User assigned role: {}", user.get().getRoleSet());
    }
}
