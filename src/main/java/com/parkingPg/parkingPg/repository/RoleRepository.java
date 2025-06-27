package com.parkingPg.parkingPg.repository;

import com.parkingPg.parkingPg.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByid(Integer id);
}
