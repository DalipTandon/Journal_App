package com.journel.Journel.App.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journel.Journel.App.Services.UserService;
import com.journel.Journel.entity.UserEntity;

@RestController
@RequestMapping("/admin-api")
public class AdminController {

    @Autowired
    public UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllAdmin(){
        List<UserEntity> allUser=userService.getAllUsers();

        if(allUser!=null && !allUser.isEmpty()){
            return new ResponseEntity<>(allUser,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public void createAdmin(@RequestBody UserEntity user){

     user.setRoles(List.of("USER","ADMIN"));
    userService.saveNewEnteries(user);

    }


}
