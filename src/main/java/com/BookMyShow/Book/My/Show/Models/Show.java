package com.BookMyShow.Book.My.Show.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    Hall hall;

    @JsonIgnore
    @ManyToOne
    Movie movie;

    @JsonIgnore
    @ManyToOne
    Screen screen;

    Date startTime;
    Date endTime;

    int price;
    int availableSeats;

    @OneToMany(mappedBy = "show")
    List<Ticket> tickets;
}
