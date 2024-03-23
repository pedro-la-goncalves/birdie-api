package com.birdie.birdie.service;

import com.birdie.birdie.model.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
    @InjectMocks
    ReservationService reservationService;

    @Test
    void givenTwoValidDates_whenCalculatingTheDurationOfTheReservation_thenReturnTheNumberOfDaysPlusOne() {
        LocalDate entry = LocalDate.of(2024, Month.MARCH, 22);
        LocalDate exit = LocalDate.of(2024, Month.MARCH, 25);

        int numberOfDays = reservationService.getDurationInDays(entry, exit);

        Assertions.assertEquals(4, numberOfDays);
    }

    @Test
    void givenAValidWeekendDayDate_whenVerifyingIfItIsAWeekendDay_thenReturnTrue() {
        LocalDate day = LocalDate.of(2024, Month.MARCH, 23);

        boolean isWeekendDay = reservationService.isWeekend(day);

        Assertions.assertTrue(isWeekendDay);
    }

    @Test
    void givenTwoValidDates_whenVerifyingHowManyWeekendDaysThePeriodHas_thenReturnTheNumberOfWeekendDays() {
        LocalDate entry = LocalDate.of(2024, Month.MARCH, 22);
        LocalDate exit = LocalDate.of(2024, Month.MARCH, 25);

        int numberOfWeekendDays = reservationService.getNumberOfWeekendDays(entry, exit);

        Assertions.assertEquals(2, numberOfWeekendDays);
    }

    @Test
    void givenTwoValidDates_whenVerifyingHowManyWorkingDaysThePeriodHas_thenReturnTheNumberOfWeekendDays() {
        LocalDate entry = LocalDate.of(2024, Month.MARCH, 22);
        LocalDate exit = LocalDate.of(2024, Month.MARCH, 25);

        int numberOfWeekendDays = reservationService.getNumberOfWorkingDays(entry, exit);

        Assertions.assertEquals(2, numberOfWeekendDays);
    }

    @Test
    void givenANumber_whenRoundingItUp_thenReturnTheSameNumberWithTwoDecimalPlaces() {
        double rate = 345.6789;

        double roundedUpRate = reservationService.getRoundedUpWithDecimalsValue(rate);

        Assertions.assertEquals(345.68, roundedUpRate);
    }

    @Test
    void givenTwoValidDates_whenCalculatingTheSumOfDailyRates_thenReturnTheSumOfDailyRates() {
        LocalDate entry = LocalDate.of(2024, Month.MARCH, 22);
        LocalDate exit = LocalDate.of(2024, Month.MARCH, 25);

        double sumOfDailyRates = reservationService.getSumOfDailyRates(entry, exit);

        Assertions.assertEquals(600.0, sumOfDailyRates);
    }

    @ParameterizedTest
    @MethodSource("provideReservationsForIsGuestCheckingOutLate")
    void givenAValidExitDateAndAValidCheckoutDateTime_whenVerifyingIfGuestIsCheckingOutLate_thenReturnIfGuestIsCheckingOutLate(
            LocalDate exit,
            LocalDateTime checkout,
            boolean expected
    ) {
        boolean isGuestCheckingOutLate = reservationService.isGuestCheckingOutLate(exit, checkout);

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
    void givenIfTheDayIsAWeekendDay_whenCalculatingTheLateCheckoutFee_thenReturnTheCorrectLateCheckoutFee(boolean isWeekend, double expected) {
        double lateCheckoutFee = reservationService.getLateCheckoutFee(isWeekend);

        Assertions.assertEquals(expected, lateCheckoutFee);
    }

    private static Stream<Arguments> provideReservationsForGetCheckingOutLateFee() {
        return Stream.of(
                Arguments.of(true, 90.0),
                Arguments.of(false, 60.0)
        );
    }

}