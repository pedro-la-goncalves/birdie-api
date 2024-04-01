package com.birdie.birdie.mvc.repository;

import com.birdie.birdie.mvc.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    @Query("SELECT g FROM Guest g INNER JOIN Reservation r ON g.id = r.id WHERE r.checkIn IS NOT NULL AND r.checkOut IS NULL")
    List<Guest> findAllInHotel();

    @Query("SELECT g FROM Guest g INNER JOIN Reservation r ON g.id = r.id WHERE r.checkIn IS NULL")
    List<Guest> findAllNonCheckedInWithReservation();
}
