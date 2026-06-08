package com.journel.Journel.App.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.journel.Journel.App.Respository.UserRepository;


@SpringBootTest
public class UserServiceTests {
    @Autowired
    public UserRepository userRepo;
    @Test
    public void testAdd(){
        assertEquals(4,2+2);
        assertNotNull(userRepo.findByUserName("Raghu")); //the value should exisits in the db as well
    }
}
