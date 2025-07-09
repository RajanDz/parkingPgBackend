package com.parkingPg.parkingPg.repository;

import com.parkingPg.parkingPg.entity.UserOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOptionRepo extends JpaRepository<UserOption, Long> {
    UserOption findByUserId(Integer userId);
}
