package com.hamza.howler.controllers;

import com.hamza.howler.dto.HowlDTO;
import com.hamza.howler.dto.mapper.HowlDTOMapper;
import com.hamza.howler.exceptions.HowlException;
import com.hamza.howler.exceptions.UserException;
import com.hamza.howler.model.Howl;
import com.hamza.howler.model.User;
import com.hamza.howler.request.HowlReplyRequest;
import com.hamza.howler.response.ApiResponse;
import com.hamza.howler.services.HowlService;
import com.hamza.howler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/howls")
public class HowlController {
    @Autowired
    private HowlService howlService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<HowlDTO> createHowl(@RequestBody Howl req, @RequestHeader("Authorization") String jwt) throws UserException, HowlException {
        User user=userService.findUserProfileByJwt(jwt);
        Howl howl=howlService.createHowl(req,user);
        HowlDTO howlDTO= HowlDTOMapper.toHowlDTO(howl,user);
        return new ResponseEntity<>(howlDTO, HttpStatus.CREATED);
    }
    @PostMapping("/reply")
    public ResponseEntity<HowlDTO> replyHowl(@RequestBody HowlReplyRequest req, @RequestHeader("Authorization") String jwt) throws UserException, HowlException {
        User user=userService.findUserProfileByJwt(jwt);
        Howl howl=howlService.createReply(req,user);
        HowlDTO howlDTO= HowlDTOMapper.toHowlDTO(howl,user);
        return new ResponseEntity<>(howlDTO, HttpStatus.CREATED);
    }
    @PutMapping("/{howlId}/retweet")
    public ResponseEntity<HowlDTO> retweet(@PathVariable Long howlId, @RequestHeader("Authorization") String jwt) throws UserException, HowlException {
        User user=userService.findUserProfileByJwt(jwt);
        Howl howl=howlService.retweet(howlId,user);
        HowlDTO howlDTO= HowlDTOMapper.toHowlDTO(howl,user);
        return new ResponseEntity<>(howlDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{howlId}")
    public ResponseEntity<HowlDTO> findHowlById(@PathVariable Long howlId, @RequestHeader("Authorization") String jwt) throws UserException, HowlException {
        User user=userService.findUserProfileByJwt(jwt);
        Howl howl=howlService.findById(howlId);
        HowlDTO howlDTO= HowlDTOMapper.toHowlDTO(howl,user);
        return new ResponseEntity<>(howlDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{howlId}")
    public ResponseEntity<ApiResponse> deleteHowl(@PathVariable Long howlId, @RequestHeader("Authorization") String jwt) throws UserException, HowlException {
        User user=userService.findUserProfileByJwt(jwt);
        howlService.deleteHowlById(howlId,user.getId());
        ApiResponse response=new ApiResponse("Howl deleted successfully",true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<HowlDTO>> getAllHowls(@RequestHeader("Authorization") String jwt) throws UserException, HowlException {
        User user=userService.findUserProfileByJwt(jwt);
        List<Howl> howls=howlService.findAllHowl();
        List<HowlDTO> howlDTOS= HowlDTOMapper.toHowlDTOS(howls,user);
        return new ResponseEntity<>(howlDTOS, HttpStatus.ACCEPTED);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HowlDTO>> getUsersHowls(@PathVariable Long userId,@RequestHeader("Authorization") String jwt) throws UserException, HowlException {
        User user=userService.findUserProfileByJwt(jwt);
        List<Howl> howls=howlService.getUserHowls(user);
        List<HowlDTO> howlDTOS= HowlDTOMapper.toHowlDTOS(howls,user);
        return new ResponseEntity<>(howlDTOS, HttpStatus.ACCEPTED);
    }
    @GetMapping("/user/{userId}/likes")
    public ResponseEntity<List<HowlDTO>> findHowlByLikesContainsUser(@PathVariable Long userId,@RequestHeader("Authorization") String jwt) throws UserException, HowlException {
        User user=userService.findUserProfileByJwt(jwt);
        List<Howl> howls=howlService.findByLikesContainsUser(user);
        List<HowlDTO> howlDTOS= HowlDTOMapper.toHowlDTOS(howls,user);
        return new ResponseEntity<>(howlDTOS, HttpStatus.ACCEPTED);
    }
}
