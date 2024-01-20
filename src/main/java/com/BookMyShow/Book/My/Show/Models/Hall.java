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
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    String address;
    String email;
    long contactNo;
    String password;

    @OneToMany(mappedBy = "hall") // Instead of creating new table for hall and screen , we use mapped by to avoid creation of new tablw
    List<Screen> screens;

    @OneToMany(mappedBy = "hall")
    List<Show> shows;

    @OneToMany(mappedBy = "hall")
    List<Ticket> tickets;
}
