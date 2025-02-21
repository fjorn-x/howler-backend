package com.hamza.howler.controllers;

import com.hamza.howler.response.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/testing")
    public ResponseEntity<AuthResponse> testing(){
        AuthResponse res=new AuthResponse("working",true);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }
}
