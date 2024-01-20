package com.BookMyShow.Book.My.Show.Models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @ManyToOne
    Hall hall;

    @ManyToOne
    Movie movie;

    @ManyToOne
    Screen screen;

    Date startTime;
    Date endTime;

    int price;
    int availableSeats;

    @OneToMany(mappedBy = "show")
    List<Ticket> tickets;
}
