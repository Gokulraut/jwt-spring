package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.AuthRequest;
import com.csi.model.User;
import com.csi.service.UserService;
import com.csi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userServiceImpl;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/saveData")
    public ResponseEntity<User>saveData(@RequestBody User user){
        return ResponseEntity.ok(userServiceImpl.saveData(user));
    }
    @GetMapping("/getDataById/{userId}")
    public ResponseEntity<Optional<User>> getDataById(@PathVariable int userId){
        return ResponseEntity.ok(userServiceImpl.getDataById(userId));
    }
    @GetMapping("/getAllData")
    public ResponseEntity<List<User>>getAllData(){
        return ResponseEntity.ok(userServiceImpl.getAllData());
    }
/*    @GetMapping("/getDataByName/{userName}")
    public ResponseEntity<User>getDataByName(@PathVariable String userName){
        return ResponseEntity.ok(userServiceImpl.findbyName(userName));
    }*/
    @PutMapping("/updateData/{userId}")
    public ResponseEntity<User>updateData(@PathVariable int userId,@RequestBody User user){

        User user1=userServiceImpl.getDataById(userId).orElseThrow(()->new RecordNotFoundException("Id Dose Not Exist"));

        user1.setUserEmailId(user.getUserEmailId());
        user1.setUserPassword(user.getUserPassword());
        user1.setUserName(user.getUserName());
        return ResponseEntity.ok(userServiceImpl.updateData(user1));
    }
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getUserPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Incorrect un/pwd");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
