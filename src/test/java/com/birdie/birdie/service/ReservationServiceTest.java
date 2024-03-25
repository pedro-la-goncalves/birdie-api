package com.birdie.birdie.service;

import com.birdie.birdie.model.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
    @InjectMocks
    @Spy
    ReservationService reservationService;

    @Test
    void givenTwoValidDates_whenCalculatingTheDurationOfTheReservation_thenReturnTheNumberOfDays() {
        LocalDate scheduledEntry = LocalDate.of(2024, Month.MARCH, 22);
        LocalDate scheduledDeparture = LocalDate.of(2024, Month.MARCH, 25);

        int numberOfDays = reservationService.getDurationInDays(scheduledEntry, scheduledDeparture);

        Assertions.assertEquals(3, numberOfDays);
    }

    @Test
    void givenAValidWeekendDayDate_whenVerifyingIfItIsAWeekendDay_thenReturnTrue() {
        LocalDate day = LocalDate.of(2024, Month.MARCH, 23);

        boolean isWeekendDay = reservationService.isWeekend(day);

        Assertions.assertTrue(isWeekendDay);
    }

    @Test
    void givenTwoValidDates_whenVerifyingHowManyWeekendDaysThePeriodHas_thenReturnTheNumberOfWeekendDays() {
        LocalDate scheduledEntry = LocalDate.of(2024, Month.MARCH, 22);
        LocalDate scheduledDeparture = LocalDate.of(2024, Month.MARCH, 25);

        int numberOfWeekendDays = reservationService.getNumberOfWeekendDays(scheduledEntry, scheduledDeparture);

        Assertions.assertEquals(2, numberOfWeekendDays);
    }

    @Test
    void givenTwoValidDates_whenVerifyingHowManyWorkingDaysThePeriodHas_thenReturnTheNumberOfWeekendDays() {
        LocalDate scheduledEntry = LocalDate.of(2024, Month.MARCH, 22);
        LocalDate scheduledDeparture = LocalDate.of(2024, Month.MARCH, 25);

        int numberOfWeekendDays = reservationService.getNumberOfWorkingDays(scheduledEntry, scheduledDeparture);

        Assertions.assertEquals(1, numberOfWeekendDays);
    }

    @Test
    void givenANumber_whenRoundingItUp_thenReturnTheSameNumberWithTwoDecimalPlaces() {
        double rate = 345.6789;

        double roundedUpRate = reservationService.getRoundedUpWithDecimalsValue(rate);

        Assertions.assertEquals(345.68, roundedUpRate);
    }

    @Test
    void givenTwoValidDates_whenCalculatingTheSumOfDailyRates_thenReturnTheSumOfDailyRates() {
        LocalDate scheduledEntry = LocalDate.of(2024, Month.MARCH, 22);
        LocalDate scheduledDeparture = LocalDate.of(2024, Month.MARCH, 25);

        double sumOfDailyRates = reservationService.getSumOfDailyRates(scheduledEntry, scheduledDeparture);

        Assertions.assertEquals(480.0, sumOfDailyRates);
    }

    @ParameterizedTest
    @MethodSource("provideReservationsForIsGuestCheckingOutLate")
    void givenAValidScheduledDepartureAndAValidCheckoutDateTime_whenVerifyingIfGuestIsCheckingOutLate_thenReturnIfGuestIsCheckingOutLate(
            LocalDate scheduledDeparture,
            LocalDateTime checkout,
            boolean expected
    ) {
        boolean isGuestCheckingOutLate = reservationService.isGuestCheckingOutLate(scheduledDeparture, checkout);

        Assertions.assertEquals(expected, isGuestCheckingOutLate);
    }

    private static Stream<Arguments> provideReservationsForIsGuestCheckingOutLate() {
        return Stream.of(
                Arguments.of(LocalDate.of(2024, Month.MARCH, 25), LocalDateTime.parse("2024-03-25T11:00:00"), false),
                Arguments.of(LocalDate.of(2024, Month.MARCH, 25), LocalDateTime.parse("2024-03-25T13:00:00"), true),
                Arguments.of(LocalDate.of(2024, Month.MARCH, 25), LocalDateTime.parse("2024-03-25T12:00:00"), false),
                Arguments.of(LocalDate.of(2024, Month.MARCH, 25), LocalDateTime.parse("2024-03-26T11:00:00"), true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideReservationsForGetCheckingOutLateFee")
    void givenIfTheDayIsAWeekendDay_whenCalculatingTheLateCheckoutFee_thenReturnTheAccordingLateCheckoutFee(boolean isWeekend, double expected) {
        double lateCheckoutFee = reservationService.getLateCheckoutFee(isWeekend);

        Assertions.assertEquals(expected, lateCheckoutFee);
    }

    private static Stream<Arguments> provideReservationsForGetCheckingOutLateFee() {
        return Stream.of(
                Arguments.of(true, 90.0),
                Arguments.of(false, 60.0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideReservationsForGetParkingFee")
    void givenTheNumberOfWorkingDaysAndNumberOfWeekendDays_whenCalculatingTheParkingFee_thenReturnTheAccordingParkingFee(
            int numberOfWorkingDays,
            int numberOfWeekendDays,
            double expected
    ) {
        double lateCheckoutFee = reservationService.getParkingFee(numberOfWorkingDays, numberOfWeekendDays);

        Assertions.assertEquals(expected, lateCheckoutFee);
    }

    private static Stream<Arguments> provideReservationsForGetParkingFee() {
        return Stream.of(
                Arguments.of(2, 0, 30.0),
                Arguments.of(0, 2, 40.0),
                Arguments.of(2, 2, 70.0),
                Arguments.of(0, 0, 0.0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideReservationsForGetSumOfAdditionalCharges")
    void givenAValidReservation_whenCalculatingTheSumOfAdditionalCharges_thenReturnTheAccordingSumOfAdditionalCharges(
            Reservation reservation,
            boolean isGuestCheckingOutLate,
            double lateCheckoutFee,
            double expected
    ) {
        if (isGuestCheckingOutLate) Mockito.doReturn(isGuestCheckingOutLate).when(reservationService).isGuestCheckingOutLate(Mockito.any(LocalDate.class), Mockito.any(LocalDateTime.class));
        if (lateCheckoutFee > 0) Mockito.doReturn(lateCheckoutFee).when(reservationService).getLateCheckoutFee(Mockito.anyBoolean());

        double sumOfAdditionalCharges = reservationService.getSumOfAdditionalCharges(reservation);

        Assertions.assertEquals(expected, sumOfAdditionalCharges);
    }

    private static Stream<Arguments> provideReservationsForGetSumOfAdditionalCharges() {
        Reservation reservation1 = new Reservation();
        reservation1.setScheduledEntry(LocalDate.of(2024, Month.MARCH, 22));
        reservation1.setScheduledDeparture(LocalDate.of(2024, Month.MARCH, 25));
        reservation1.setCheckOut(LocalDateTime.parse("2024-03-25T13:00:00"));

        Reservation reservation2 = new Reservation();
        reservation2.setScheduledEntry(LocalDate.of(2024, Month.MARCH, 22));
        reservation2.setScheduledDeparture(LocalDate.of(2024, Month.MARCH, 25));
        reservation2.setCheckOut(LocalDateTime.parse("2024-03-25T10:00:00"));

        return Stream.of(
                Arguments.of(reservation1, true, 70.0, 70.0),
                Arguments.of(reservation2, false, 0.0, 0.0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideReservationsForGetEstimatedTotal")
    void givenAValidReservation_whenCalculatingTheEstimatedTotal_thenReturnTheAccordingEstimatedTotal(
            Reservation reservation,
            double parkingFee,
            double sumOfDailyRates,
            double expected
    ) {
        if (parkingFee > 0) Mockito.doReturn(parkingFee).when(reservationService).getParkingFee(Mockito.anyInt(), Mockito.anyInt());
        if (sumOfDailyRates > 0) Mockito.doReturn(sumOfDailyRates).when(reservationService).getSumOfDailyRates(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));

        double estimatedTotal = reservationService.getEstimatedTotal(reservation);

        Assertions.assertEquals(expected, estimatedTotal);
    }

    private static Stream<Arguments> provideReservationsForGetEstimatedTotal() {
        Reservation reservation1 = new Reservation();
        reservation1.setScheduledEntry(LocalDate.of(2024, Month.MARCH, 22));
        reservation1.setScheduledDeparture(LocalDate.of(2024, Month.MARCH, 25));
        reservation1.setParking(false);

        Reservation reservation2 = new Reservation();
        reservation2.setScheduledEntry(LocalDate.of(2024, Month.MARCH, 23));
        reservation2.setScheduledDeparture(LocalDate.of(2024, Month.MARCH, 25));
        reservation2.setParking(false);

        Reservation reservation3 = new Reservation();
        reservation3.setScheduledEntry(LocalDate.of(2024, Month.MARCH, 25));
        reservation3.setScheduledDeparture(LocalDate.of(2024, Month.MARCH, 27));
        reservation3.setParking(false);

        Reservation reservation4 = new Reservation();
        reservation4.setScheduledEntry(LocalDate.of(2024, Month.MARCH, 22));
        reservation4.setScheduledDeparture(LocalDate.of(2024, Month.MARCH, 25));
        reservation4.setParking(true);

        return Stream.of(
                Arguments.of(reservation1, 0.0, 480.0, 480.0),
                Arguments.of(reservation2, 0.0, 360.0, 360.0),
                Arguments.of(reservation3, 0.0, 300.0, 300.0),
                Arguments.of(reservation4, 30.0, 240.0, 270.0)
        );
    }

}