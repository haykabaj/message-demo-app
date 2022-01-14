package hayk.abajyan.apps.am.controller;

import com.sun.istack.NotNull;
import hayk.abajyan.apps.am.exception.DataBaseException;
import hayk.abajyan.apps.am.model.Message;
import hayk.abajyan.apps.am.model.User;
import hayk.abajyan.apps.am.service.MessageService;
import hayk.abajyan.apps.am.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 The controller below intended to convey a message between users
 Created by Hayk Abajyan
 14/01/2022

             ATTENTION!!!

 please don't repeat that  at home , it can harm you and your loved ones
 **/

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserServiceImpl userService;
    private final MessageService  messageService;

    @Autowired
    public UserController(UserServiceImpl userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody @NotNull User user) {
        if (this.userService.userExist(user.getEmail()) == null) {
            this.userService.add(user);
            return ResponseEntity.ok("user successfully added");
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("user with this  email already exist or the entered data is incorrect");
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByEmailAndPassword(@RequestParam("email") String email, @RequestParam("password") String password){
        try {
            Optional<User> userEntity = this.userService.get(email, password);
            return userEntity.map(ResponseEntity::ok).orElseGet(()
                    -> ResponseEntity.notFound().build());
        } catch (DataBaseException e) {
            logger.error("Error", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody Message message){
        try {
            this.messageService.add(message);
            return ResponseEntity.ok("message successfully added");
        } catch (DataBaseException e) {
            logger.error("message note sent", e);
        }
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body("message not sent");
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getUserConversation(@RequestParam("userId") int userId,
                                                      @RequestParam("interlocutorId") int interlocutorId){
        List<Message> conversationMessages = null;
        try {
            conversationMessages = this.messageService.getAll(userId, interlocutorId);
            if (conversationMessages.isEmpty()){
                logger.warn("no messages");
                return ResponseEntity.ok(Collections.emptyList());
            }
        } catch (DataBaseException e) {
            logger.error("sql syntax error", e);
        }
        return ResponseEntity.ok(conversationMessages);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId){
        userService.delete(userId);
       return ResponseEntity.ok("user deleted");
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}