package com.BookMyShow.Book.My.Show.Controller;


import com.BookMyShow.Book.My.Show.DTO.request.AddScreenDTO;
import com.BookMyShow.Book.My.Show.DTO.request.AddShowDTO;
import com.BookMyShow.Book.My.Show.DTO.request.HallOwnerSignUpDTO;
import com.BookMyShow.Book.My.Show.DTO.response.GeneralMessageDTO;
import com.BookMyShow.Book.My.Show.Exceptions.ResourceNotExists;
import com.BookMyShow.Book.My.Show.Exceptions.UnAuthorized;
import com.BookMyShow.Book.My.Show.Exceptions.UserDoesNotExists;
import com.BookMyShow.Book.My.Show.Models.ApplicationUser;
import com.BookMyShow.Book.My.Show.Models.Hall;
import com.BookMyShow.Book.My.Show.Models.Screen;
import com.BookMyShow.Book.My.Show.Models.Show;
import com.BookMyShow.Book.My.Show.Service.HallOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hall")
public class HallOwnerController {
    @Autowired
    HallOwnerService hallOwnerService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody HallOwnerSignUpDTO hallOwnerSignUpDTO){
        ApplicationUser user = hallOwnerService.signUp(hallOwnerSignUpDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/addScreen")
    public ResponseEntity addScreen(@RequestBody AddScreenDTO addScreenDTO, @RequestParam String email){
        try {
            hallOwnerService.addScreen(addScreenDTO,email);
        } catch (UserDoesNotExists e){
            return new ResponseEntity<>(new GeneralMessageDTO(e.getMessage()),HttpStatus.NOT_FOUND);//404
        } catch (UnAuthorized e){
            return new ResponseEntity(new GeneralMessageDTO(e.getMessage()),HttpStatus.UNAUTHORIZED); //401
        } catch (ResourceNotExists e){
            return new ResponseEntity<>(new GeneralMessageDTO(e.getMessage()),HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(new GeneralMessageDTO("Screen added Successfully"),HttpStatus.CREATED); //201
    }

    @PostMapping("/createShows")
    public ResponseEntity createShows(@RequestBody AddShowDTO addShowDTO, @RequestParam String email){

        try{
            Show show = hallOwnerService.createShows(addShowDTO,email);
            return new ResponseEntity<>(show,HttpStatus.CREATED);
        } catch(UserDoesNotExists e) {
            return new ResponseEntity<>(new GeneralMessageDTO(e.getMessage()),HttpStatus.UNAUTHORIZED);
        } catch (UnAuthorized e){
            return new ResponseEntity<>(new GeneralMessageDTO(e.getMessage()),HttpStatus.UNAUTHORIZED);
        } catch (ResourceNotExists e){
            return new ResponseEntity<>(new GeneralMessageDTO(e.getMessage()),HttpStatus.UNAUTHORIZED);
        }


    }


}
