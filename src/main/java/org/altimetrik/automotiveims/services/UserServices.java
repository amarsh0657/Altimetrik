package org.altimetrik.automotiveims.services;

import org.altimetrik.automotiveims.entites.User;
import org.altimetrik.automotiveims.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private List<User> store = new ArrayList<>();

    public List<User> getStore() {
        return this.store;
    }

    public  List<User> getUsers(){
        return userRepo.findAll();
    }

    public  User createUser(User user){
        //  user.setUserId(UUID.randomUUID().toString());
        user.setRole(user.getRole().toUpperCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

}
