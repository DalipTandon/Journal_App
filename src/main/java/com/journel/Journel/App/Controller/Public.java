package com.journel.Journel.App.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.journel.Journel.App.Services.UserService;
import com.journel.Journel.entity.UserEntity;

@RestController

public class Public {

    @Autowired
   public UserService userService ;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok-journel-app";
    }

    @PostMapping("/public/users")
    public void saveUserDetails(@RequestBody UserEntity user){
        // userService.saveEnteries(user);
        
        user.setRoles(List.of("USER"));
        userService.saveNewEnteries(user);
    }
}
