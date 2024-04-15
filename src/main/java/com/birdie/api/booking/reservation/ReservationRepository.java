package com.birdie.api.booking.reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Page<Reservation> findAllByArrivalEqualsOrderByGuest_name(Pageable pageable, LocalDate date);

    Page<Reservation> findAllByDepartureEqualsOrderByGuest_name(Pageable pageable, LocalDate date);

}
