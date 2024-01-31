package com.BookMyShow.Book.My.Show.DTO.request;


import com.BookMyShow.Book.My.Show.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegularUserSignUpDTO {

    UUID id;
    String name;
    String email;
    String password;
    long mobileNo;
    UserType type;
    int age;
}
