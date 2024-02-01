package com.BookMyShow.Book.My.Show.Repository;

import com.BookMyShow.Book.My.Show.Models.Show;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface ShowRepository extends JpaRepository<Show, UUID> {

    @Query(value = "select * from show where movie_id =:id",nativeQuery = true)
    public List<Show> getShowByMovieId(UUID id);

    @Query(value = "select * from show where hall_id =:id",nativeQuery = true)
    public List<Show> getShowByHallId(UUID id);

    @Query(value = "select * from show where movie_id =:movie_id and hall_id =:hall_id ",nativeQuery = true)
    public List<Show> getShowsByMovieAndHallId(UUID movie_id,UUID hall_id);

    @Modifying
    @Transactional
    @Query(value = "update show set available_seats =:updatedAvailableSeats where id =:showId",nativeQuery = true)
    public void updateTicketCount(UUID showId,int updatedAvailableSeats);
}
