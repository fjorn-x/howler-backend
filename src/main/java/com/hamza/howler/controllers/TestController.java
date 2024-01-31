package com.hamza.howler.controllers;

import com.hamza.howler.response.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping("/.well-known/pki-validation/6B7EF7003F13D16B83A0718BB760E5A9.txt")
    public ResponseEntity<String> ssl()throws Exception{

//        Path path = Path.of("C:\\JavaProjects\\howler\\howler-backend\\src\\main\\resources\\6B7EF7003F13D16B83A0718BB760E5A9.txt");
//        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        String res="3A2954B035CEA6DDE3F12F4C3F2006EE437146AFBC4B9EF4AF3F857E21899414\n" +
                "comodoca.com\n" +
                "bce187cd9561df6";
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(res);

    }
}
