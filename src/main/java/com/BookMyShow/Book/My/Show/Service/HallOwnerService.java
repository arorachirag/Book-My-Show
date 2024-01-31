package com.BookMyShow.Book.My.Show.Service;

import com.BookMyShow.Book.My.Show.DTO.request.AddScreenDTO;
import com.BookMyShow.Book.My.Show.DTO.request.AddShowDTO;
import com.BookMyShow.Book.My.Show.DTO.request.HallOwnerSignUpDTO;
import com.BookMyShow.Book.My.Show.Exceptions.ResourceNotExists;
import com.BookMyShow.Book.My.Show.Exceptions.UnAuthorized;
import com.BookMyShow.Book.My.Show.Exceptions.UserDoesNotExists;
import com.BookMyShow.Book.My.Show.Models.*;
import com.BookMyShow.Book.My.Show.Repository.ApplicationUserRepository;
import com.BookMyShow.Book.My.Show.Repository.HallRepository;
import com.BookMyShow.Book.My.Show.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class HallOwnerService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    RegularUserService regularUserService;
    @Autowired
    HallRepository hallRepository;

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ScreenService screenService;

    @Autowired
    ShowService showService;

    public ApplicationUser signUp(HallOwnerSignUpDTO hallOwnerSignUpDTO){
        ApplicationUser user = new ApplicationUser();
        user.setName(hallOwnerSignUpDTO.getName());
        user.setEmail(hallOwnerSignUpDTO.getEmail());
        user.setPassword(hallOwnerSignUpDTO.getPassword());
        user.setMobileNo(hallOwnerSignUpDTO.getMobileNo());
        user.setType(hallOwnerSignUpDTO.getType().toString());

        //System.out.println(user.getId());
        applicationUserRepository.save(user);
        //System.out.println(user.getId());
        List<Hall> halls = hallOwnerSignUpDTO.getHalls();

        for(Hall hall : halls){
            hall.setOwner(user);
            hallRepository.save(hall);
        }

        return user;
    }

    public Hall getHallById(UUID id){
        return hallRepository.findById(id).orElse(null);
    }

    public Movie getMovieById(UUID id){ return movieRepository.findById(id).orElse(null);}
    public void addScreen(AddScreenDTO addScreenDTO,String email){
        List<Screen> screenList = addScreenDTO.getScreenList();
        UUID hallId = addScreenDTO.getHallId();

        ApplicationUser user = regularUserService.getUserByEmail(email);

        if(user==null){
            throw new UserDoesNotExists("User with this mail doesn't exists");
        }

        if(!user.getType().equals("HallOwner")){
            throw new UnAuthorized("User not allowed");
        }

        Hall hall = getHallById(hallId);

        if(hall==null){
            throw new ResolutionException(String.format("Hall with id %s doesn't exists in the system", hallId.toString()));

        }

        if(hall.getOwner().getId() != user.getId()){
            throw new UnAuthorized("User does not own this hallId");
        }

        for(Screen screen : screenList){
            screen.setHall(hall);
            screenService.registerScreen(screen);
        }



    }

    public Show createShows(AddShowDTO addShowDTO,String email){
        ApplicationUser user = applicationUserRepository.findUserByEmail(email);

        if(user==null){
            throw new UserDoesNotExists(String.format("User with email id %s doesn't exists",email));
        }

        if(!user.getType().equals("HallOwner")){
            throw new UnAuthorized(String.format("User with email id %s not allowed",email));
        }

        UUID hallId = addShowDTO.getHallId();
        Hall hall = getHallById(hallId);

        if(hall==null){
            throw new ResourceNotExists(String.format("Hall with id %s doesn't exists",hallId.toString()));
        }


        if(hall.getOwner().getId()!=user.getId()){
            throw new UnAuthorized(String.format("User with emailId %s does not own this hall with id %s",email,hallId.toString()));
        }

        UUID movieId = addShowDTO.getMovieId();
        Movie movie = getMovieById(movieId);

        if(movie==null){
            throw new ResourceNotExists(String.format("Movie with movir id %s doesn't exists",movieId.toString()));
        }

        List<Screen> screenList = new ArrayList<>();

        for(Screen screen : hall.getScreens()){
            if(screen.isStatus()==false){
                screenList.add(screen);
            }
        }

        if(screenList.size()==0){
            throw new ResourceNotExists(String.format("Hall with hall id %s does not have any seats left",hallId.toString()));
        }

        Screen screen = screenList.get(0);
        Show show = new Show();

        show.setHall(hall);
        show.setMovie(movie);
        show.setAvailableSeats(screen.getCapacity());
        show.setPrice(addShowDTO.getPrice());
        show.setScreen(screen);
        Date startDate = new Date();
        startDate.setHours(addShowDTO.getHour());
        startDate.setMinutes(addShowDTO.getMinutes());

        Date endDate = new Date();
        endDate.setHours((addShowDTO.getHour()+(int)movie.getDuration())%24);
        endDate.setMinutes((addShowDTO.getMinutes()));

        show.setStartTime(startDate);
        show.setEndTime(endDate);

        screenService.bookScreen(screen.getId());

        showService.createShow(show);

        return show;
    }
}
