package com.BookMyShow.Book.My.Show.Controller;

import com.BookMyShow.Book.My.Show.Models.Show;
import com.BookMyShow.Book.My.Show.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @GetMapping("/search")
    public ResponseEntity searchByMovieAndHallId(@RequestParam(required = false) UUID movieId, @RequestParam(required = false) UUID hallId){

        if(movieId!=null && hallId!=null){
            List<Show> show = showService.getShowsByMovieAndHallId(movieId,hallId);
            return new ResponseEntity(show,HttpStatus.OK);
        } else if(movieId!=null && hallId==null){
            List<Show> show = showService.getShowsByMovieId(movieId);
            return new ResponseEntity(show,HttpStatus.OK);

        } else if(hallId!=null && movieId==null){
            List<Show> show = showService.getShowsByHallId(hallId);
            return new ResponseEntity(show,HttpStatus.OK);
        } else {
            return new ResponseEntity("Please pass atleast one param",HttpStatus.OK);
        }

    }


}
