package com.BookMyShow.Book.My.Show.Models;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    double rating;
    double duration;


    @OneToMany(mappedBy = "movie")
    List<Ticket> tickets;

    @ManyToOne
    ApplicationUser owner;
}
