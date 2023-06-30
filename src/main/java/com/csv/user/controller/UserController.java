package com.csv.user.controller;

import com.csv.user.model.UserEntity;
import com.csv.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
////            Swagger-UI --->     http://localhost:8989/swagger-ui.html
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    ResponseEntity<String> createUser(@RequestBody UserEntity user){
        try{
            UserEntity newUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User with id : " + newUser.getUserId() + " created successfully");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Can't create user : ---> " + e.toString());
        }
    }
    //          http://localhost:8989/user/

    @GetMapping("/{id}")
    ResponseEntity<?> getUserById(@PathVariable Integer id){
        try{
            UserEntity user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.toString());
        }
    }
    //          http://localhost:8989/user/2

    @PutMapping("/{id}")
    ResponseEntity<String> updateUser(@RequestBody UserEntity updatedUser, @PathVariable Integer id){
        try{
            userService.updateUserById(updatedUser,id);
            return ResponseEntity.ok("User with id : " + id + " updated successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.toString());
        }
    }
    //          http://localhost:8989/user/3

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUserById(@PathVariable Integer id){
        try{
            userService.deleteUserById(id);
            return ResponseEntity.ok("User with id : " + id  +" deleted successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.toString());
        }
    }
    //          http://localhost:8989/user/1

    @GetMapping("/users")
    ResponseEntity<?> getAllUsers(){
        try{
            List<UserEntity> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.toString());
        }
    }
    //          http://localhost:8989/user/users

    @GetMapping("/boys")
    ResponseEntity<?> getAllBoys(){
        try{
            List<UserEntity> boys = userService.getAllBoys();
            return ResponseEntity.ok(boys);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.toString());
        }
    }
    //          http://localhost:8989/user/boys

    @GetMapping("/girls")
    ResponseEntity<?> getAllGirls(){
        try{
            List<UserEntity> girls = userService.getAllGirls();
            return ResponseEntity.ok(girls);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.toString());
        }
    }
    //          http://localhost:8989/user/girls

    @GetMapping("/adults")
    ResponseEntity<?> getAllAdults(){
        try{
            List<UserEntity> adults = userService.getAllAdults();
            return ResponseEntity.ok(adults);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.toString());
        }
    }
    //          http://localhost:8989/user/adults
}
