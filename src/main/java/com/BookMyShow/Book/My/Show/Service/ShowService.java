package com.BookMyShow.Book.My.Show.Service;


import com.BookMyShow.Book.My.Show.Models.Show;
import com.BookMyShow.Book.My.Show.Repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    public void createShow(Show show){
        showRepository.save(show);
    }
}
