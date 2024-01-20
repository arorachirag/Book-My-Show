package com.BookMyShow.Book.My.Show.Models;


import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @ManyToOne
    ApplicationUser applicationUser;

    @ManyToOne
    Movie movie;

    @ManyToOne
    Hall hall;

    @ManyToOne
    Show show;

}
