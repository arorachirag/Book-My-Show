package com.BookMyShow.Book.My.Show.Service;


import com.BookMyShow.Book.My.Show.DTO.request.MovieOwnerSignUpDTO;
import com.BookMyShow.Book.My.Show.Models.ApplicationUser;
import com.BookMyShow.Book.My.Show.Models.Movie;
import com.BookMyShow.Book.My.Show.Repository.ApplicationUserRepository;
import com.BookMyShow.Book.My.Show.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class MovieOwnerService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    MovieRepository movieRepository;

    public ApplicationUser signUp(MovieOwnerSignUpDTO movieOwnerSignUpDTO){
        ApplicationUser movieOwner = new ApplicationUser();

        movieOwner.setName(movieOwnerSignUpDTO.getName());
        movieOwner.setEmail(movieOwnerSignUpDTO.getEmail());
        movieOwner.setPassword(movieOwnerSignUpDTO.getPassword());
        movieOwner.setMobileNo(movieOwnerSignUpDTO.getMobileNo());
        movieOwner.setType(movieOwnerSignUpDTO.getType().toString());

        applicationUserRepository.save(movieOwner);

        List<Movie> movies = movieOwnerSignUpDTO.getMovies();
        for(Movie movie : movies){
            movie.setOwner(movieOwner);
            movieRepository.save(movie);
        }

        //applicationUserRepository.save(movieOwner);
        return movieOwner;
    }

    public Movie getMovieById(UUID id){
        return movieRepository.findById(id).orElse(null);
    }
}
