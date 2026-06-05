package com.journel.Journel.App.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.journel.Journel.App.Respository.UserRepository;
import com.journel.Journel.entity.UserEntity;

@Component
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUserName(Username);
        if (user != null) {
            UserDetails userDetails = User.builder().username(user.getUserName()).password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
            return userDetails;
        }
        throw new UsernameNotFoundException("User with this UserName not found" + Username);
    }
}
