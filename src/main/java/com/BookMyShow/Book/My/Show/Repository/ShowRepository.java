package com.BookMyShow.Book.My.Show.Repository;

import com.BookMyShow.Book.My.Show.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ShowRepository extends JpaRepository<Show, UUID> {
}
