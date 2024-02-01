package com.BookMyShow.Book.My.Show.Service;


import com.BookMyShow.Book.My.Show.Exceptions.ResourceNotExists;
import com.BookMyShow.Book.My.Show.Exceptions.UnAuthorized;
import com.BookMyShow.Book.My.Show.Exceptions.UserDoesNotExists;
import com.BookMyShow.Book.My.Show.Models.*;
import com.BookMyShow.Book.My.Show.Repository.ApplicationUserRepository;
import com.BookMyShow.Book.My.Show.Repository.ShowRepository;
import com.BookMyShow.Book.My.Show.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    ShowService showService;

    @Autowired
    MovieOwnerService movieOwnerService;

    @Autowired
    HallOwnerService hallOwnerService;

    @Autowired
    MailService mailService;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    TicketRepository ticketRepository;
    public Ticket buyTicket(String email, UUID showId){

        // Get user by email
        ApplicationUser user = applicationUserRepository.findUserByEmail(email);

        if(user==null){
            throw new UserDoesNotExists(String.format("User with email id %s doesn't exists in system",email));
        }

        // Check user has required access
        if(!user.getType().equals("RegularUser")){
            throw new UnAuthorized(String.format("User with email Id %s doesn't have access",email));
        }

        Show show = showService.getShowById(showId);
        if(show==null){
            throw new ResourceNotExists(String.format("Show id %s doesn't exist",showId));
        }

        showService.updateTicketCount(show);

        Ticket ticket = new Ticket();
        ticket.setHall(show.getHall());
        ticket.setMovie(show.getMovie());
        ticket.setShow(show);
        ticket.setApplicationUser(user);

        ticketRepository.save(ticket);

        Movie movie = movieOwnerService.getMovieById(show.getMovie().getId());
        Hall hall = hallOwnerService.getHallById(show.getHall().getId());
        // User ticket email
        String userMessage = String.format("Hey %s,\n" +
                "Congratulations your ticket got booked. Below are the details:\n" +
                "1. Movie Name - %s,\n" +
                "2. Hall Name - %s,\n" +
                "3. Date and timings - %s",user.getName(),movie.getName(),hall.getName(),show.getStartTime().toString());

        mailService.generateMail(user.getEmail(),"Ticket Confirmation",userMessage);
        return ticket;
    }


}
