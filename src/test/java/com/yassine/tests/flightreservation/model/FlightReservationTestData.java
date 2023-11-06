package com.yassine.tests.flightreservation.model;

public record FlightReservationTestData(String noOfPassengers,
                                        String expectedPrice,
                                        String firstName,
                                        String lastName,
                                        String email,
                                        String password,
                                        String street,
                                        String city,
                                        String zip
                                        ) {
}
