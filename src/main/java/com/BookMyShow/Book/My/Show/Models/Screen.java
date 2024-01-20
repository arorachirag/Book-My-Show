package com.BookMyShow.Book.My.Show.Models;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String screenName;
    @ManyToOne
    Hall hall;
    int capacity;
    boolean status;
    String type; // 2D/3D
}
