package com.csv.user.repository;

import com.csv.user.model.Gender;
import com.csv.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findByGender(Gender gender);
}
