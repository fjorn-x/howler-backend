package com.hamza.howler.controllers;

import com.hamza.howler.dto.HowlDTO;
import com.hamza.howler.services.HowlService;
import com.hamza.howler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/howls")
public class HowlController {
    @Autowired
    private HowlService howlService;
    @Autowired
    private UserService userService;

    public ResponseEntity<HowlDTO>
}
