package com.hamza.howler.controllers;

import com.hamza.howler.response.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class TestController {
    @GetMapping("/testing")
    public ResponseEntity<AuthResponse> testing(){
        AuthResponse res=new AuthResponse("working",true);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @GetMapping("/.well-known/pki-validation/6B7EF7003F13D16B83A0718BB760E5A9.txt")
    public String ssl()throws Exception{
        Path path = Path.of("C:\\JavaProjects\\howler\\howler-backend\\6B7EF7003F13D16B83A0718BB760E5A9.txt");
        return Files.readString(path);
    }
}
