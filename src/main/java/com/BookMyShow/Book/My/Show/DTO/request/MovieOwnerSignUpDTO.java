package com.BookMyShow.Book.My.Show.DTO.request;


import com.BookMyShow.Book.My.Show.Models.Movie;
import com.BookMyShow.Book.My.Show.enums.UserType;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MovieOwnerSignUpDTO {

    String name;
    String email;
    String password;
    long mobileNo;
    UserType type;
    List<Movie> movies;
}
