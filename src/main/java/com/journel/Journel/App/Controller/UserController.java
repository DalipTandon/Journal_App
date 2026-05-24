package com.journel.Journel.App.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journel.Journel.App.Services.UserService;
import com.journel.Journel.entity.UserEntity;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping
    public List<UserEntity> getAllUsrs(){
        return userService.getAllUsers();
    }

    @PostMapping
    public void saveUserDetails(@RequestBody UserEntity user){
        userService.saveEnteries(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user,@PathVariable String userName){
    UserEntity  userInDb=userService.findUserByUsername(userName);   
    if(userInDb!=null){
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveEnteries(userInDb);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
