package com.BookMyShow.Book.My.Show.Service;


import com.BookMyShow.Book.My.Show.Models.Show;
import com.BookMyShow.Book.My.Show.Repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    public void createShow(Show show){

        showRepository.save(show);
    }

    public List<Show> getShowsByMovieId(UUID movieId){
        return showRepository.getShowByMovieId(movieId);
    }

    public List<Show> getShowsByHallId(UUID hallId){

        return showRepository.getShowByHallId(hallId);
    }

    public List<Show> getShowsByMovieAndHallId(UUID movieId,UUID hallId){
        return showRepository.getShowsByMovieAndHallId(movieId,hallId);
    }

    public Show getShowById(UUID showId){
        return showRepository.findById(showId).orElse(null);
    }

    public void updateTicketCount(Show show){
        int updatedAvailableTicket = show.getAvailableSeats()-1;
        UUID showId = show.getId();
        showRepository.updateTicketCount(showId,updatedAvailableTicket);
    }
}
