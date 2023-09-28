package com.hamza.howler.controllers;

import com.hamza.howler.services.JwtProvider;
import com.hamza.howler.exceptions.UserException;
import com.hamza.howler.model.User;
import com.hamza.howler.model.Verification;
import com.hamza.howler.repository.UserRepository;
import com.hamza.howler.response.AuthResponse;
import com.hamza.howler.services.CustomUserDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired

    private JwtProvider jwtProvider;
    @Autowired
    private CustomUserDetailsServiceImplementation customUserDetailsService;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {
        String email=user.getEmail();
        String password=user.getPassword();
        String fullName=user.getFullName();
        String birthDate=user.getBirthDate();

        User isEmailExist=userRepository.findByEmail(email);
        if(isEmailExist!=null){
            throw new UserException("Email is already used with another account");
        }

        User newUser= new User();
        newUser.setEmail(email);
        newUser.setFullName(fullName);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setBirthDate(birthDate);
        newUser.setVerification(new Verification());

        User savedUser=userRepository.save(newUser);

        Authentication authentication=new UsernamePasswordAuthenticationToken(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=jwtProvider.generateJwt(authentication);
        AuthResponse res=new AuthResponse(jwt,true);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody User user){
        String email=user.getEmail();
        String password=user.getPassword();

        Authentication authentication=authenticate(email,password);
        String jwt=jwtProvider.generateJwt(authentication);
        AuthResponse res=new AuthResponse(jwt,true);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);

    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails= customUserDetailsService.loadUserByUsername(email);

        if(userDetails==null){
            throw new BadCredentialsException("Invalid Username");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Username or Password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }


}
