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
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    @Column(unique = true)
    String email;
    String password;
    @Column(unique = true)
    long mobileNo;
    String type;
    int age;

    @OneToMany(mappedBy = "applicationUser")
    List<Ticket> tickets;
}
