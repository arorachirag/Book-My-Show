package com.BookMyShow.Book.My.Show.Controller;


import com.BookMyShow.Book.My.Show.DTO.request.MovieOwnerSignUpDTO;
import com.BookMyShow.Book.My.Show.Models.ApplicationUser;
import com.BookMyShow.Book.My.Show.Service.MovieOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieOwnerController {
    @Autowired
    MovieOwnerService movieOwnerService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody MovieOwnerSignUpDTO movieOwnerSignUpDTO){
        ApplicationUser user = movieOwnerService.signUp(movieOwnerSignUpDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
