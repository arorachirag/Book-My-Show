package com.BookMyShow.Book.My.Show.DTO.request;

import com.BookMyShow.Book.My.Show.Models.Screen;
import lombok.*;

import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddScreenDTO {
    UUID hallId;
    List<Screen> screenList;
}
