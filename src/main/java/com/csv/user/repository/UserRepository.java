package com.csv.user.repository;

import com.csv.user.model.Gender;
import com.csv.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findByGender(Gender gender);

    @Query("SELECT u FROM UserEntity u WHERE u.dateOfBirth <= :date")
    List<UserEntity> findAdults(@Param("date")LocalDate date);
}
