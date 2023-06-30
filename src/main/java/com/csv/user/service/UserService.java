package com.csv.user.service;

import com.csv.user.exceptions.UserNotFoundException;
import com.csv.user.model.Gender;
import com.csv.user.model.UserEntity;
import com.csv.user.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public UserEntity createUser(UserEntity newUser){
        return userRepo.save(newUser);
    }

    public UserEntity getUserById(Integer id){
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id : " + id + " not found" ));
    }

    public void updateUserById(UserEntity updatedUser, Integer id){
        if(! userRepo.existsById(id)){
            throw new UserNotFoundException("User with id : " + id + " not found");
        }
        updatedUser.setUserId(id);
        userRepo.save(updatedUser);
    }

    public void deleteUserById(Integer id){
        if(! userRepo.existsById(id)){
            throw new UserNotFoundException("User with id : " + id + " not found");
        }
        userRepo.deleteById(id);
    }

    public List<UserEntity> getAllUsers(){
        List<UserEntity> users = userRepo.findAll();
        if(users.isEmpty()){
            throw new UserNotFoundException("No data found or There are no users in the database" );
        }
        return users;
    }

    public List<UserEntity> getAllBoys(){
        List<UserEntity> users = userRepo.findByGender(Gender.MALE);
        if(users.isEmpty()){
            throw new UserNotFoundException("No data found or There are no male users in the database" );
        }
        return users;
    }
    // this is using directly JpaRepo...this is very efficient

    public List<UserEntity> getAllGirls() {
        List<UserEntity> users = getAllUsers();
        if(users.isEmpty()){
            throw new UserNotFoundException("No data found or There are no users in the database" );
        }
        return users.stream()
                .filter(user -> user.getGender().toString().equalsIgnoreCase("female"))
                .collect(Collectors.toList());
    }
    // this is using streamAPI

    public List<UserEntity> getAllAdults(){
        LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);
        List<UserEntity> adults = userRepo.findAdults(eighteenYearsAgo);
        if(adults.isEmpty()){
            throw new UserNotFoundException("No data found or There are no users in the database" );
        }
        return adults;
    }
}
