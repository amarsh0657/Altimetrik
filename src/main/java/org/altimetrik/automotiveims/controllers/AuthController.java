package org.altimetrik.automotiveims.controllers;


import org.altimetrik.automotiveims.dto.JwtRequest;
import org.altimetrik.automotiveims.dto.JwtResponse;
import org.altimetrik.automotiveims.entites.User;
import org.altimetrik.automotiveims.repo.UserRepo;
import org.altimetrik.automotiveims.security.JwtHelper;
import org.altimetrik.automotiveims.services.CustomUserDetailsService;
import org.altimetrik.automotiveims.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {


    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserServices userServices;






    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        this.doAuthenticate(request.getUsername(), request.getPassword());

        //   UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);

        User user = (User) customUserDetailsService.loadUserByUsername(request.getUsername());

        JwtResponse jwtResponse = JwtResponse.builder().jwtToken(token)
                .username(userDetails.getUsername()).role(user.getRole()).name(user.getName()).userId(user.getUserId()).build();
        return new ResponseEntity<>(jwtResponse,HttpStatus.OK);


    }
    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password  !!");
        }

    }


    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userServices.createUser(user);

    }



}
