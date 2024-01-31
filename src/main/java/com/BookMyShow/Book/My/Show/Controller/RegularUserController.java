package com.BookMyShow.Book.My.Show.Controller;


import com.BookMyShow.Book.My.Show.DTO.request.RegularUserSignUpDTO;
import com.BookMyShow.Book.My.Show.Models.ApplicationUser;
import com.BookMyShow.Book.My.Show.Service.RegularUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class RegularUserController {
    @Autowired
    RegularUserService regularUserService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody RegularUserSignUpDTO regularUserSignUpDTO){
        ApplicationUser user = regularUserService.signUp(regularUserSignUpDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
