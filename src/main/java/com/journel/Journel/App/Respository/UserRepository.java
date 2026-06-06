package com.journel.Journel.App.Respository;

import org.bson.types.ObjectId;
// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.journel.Journel.entity.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity,ObjectId>{

    UserEntity findByUserName(String UserName);
    void deleteByUserName(String UserName);
    
} 