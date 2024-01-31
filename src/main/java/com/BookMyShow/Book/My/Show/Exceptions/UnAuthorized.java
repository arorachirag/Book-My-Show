package com.BookMyShow.Book.My.Show.Exceptions;

public class UnAuthorized extends RuntimeException{
    public UnAuthorized(String msg){
        super(msg);
    }
}
