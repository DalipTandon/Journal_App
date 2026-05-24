package com.journel.Journel.App.Services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Component;

import com.journel.Journel.App.Respository.UserRepository;
import com.journel.Journel.entity.UserEntity;
@Component
public class UserService {
    
    @Autowired
    private UserRepository userRepo;

    public void saveEnteries(UserEntity user){
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        userRepo.save(user);
    }

    public List<UserEntity> getAllUsers(){
       return userRepo.findAll();
    }

    public Optional<UserEntity> getUserbyId(ObjectId myId){
        return userRepo.findById(myId);
    }
    public void deleteById(ObjectId myId){
         userRepo.deleteById(myId);
    }
    public UserEntity findUserByUsername(String username){
        return userRepo.findByUserName(username);
    }
}
