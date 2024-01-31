package com.BookMyShow.Book.My.Show.DTO.request;


import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddShowDTO {

    int hour;
    int minutes;
    int price;
    UUID movieId;
    UUID hallId;
}
