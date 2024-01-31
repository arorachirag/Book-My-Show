package com.BookMyShow.Book.My.Show.Exceptions;

public class UserDoesNotExists extends RuntimeException{

    public UserDoesNotExists(String message){
        super(message);
    }
}
