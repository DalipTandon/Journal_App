package com.journel.Journel.App.Controller;

// import java.net.Authenticator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journel.Journel.App.Services.UserService;
import com.journel.Journel.entity.UserEntity;

import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    public UserService userService;

        @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping
    public List<UserEntity> getAllUsrs(){
        return userService.getAllUsers();
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        UserEntity  userInDb=userService.findUserByUsername(userName);   
    
        userInDb.setUserName(user.getUserName());
        // userInDb.setPassword(user.getPassword());
        userInDb.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.saveEnteries(userInDb);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public void deleteUser(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByUserName(authentication.getName());
    }

}
