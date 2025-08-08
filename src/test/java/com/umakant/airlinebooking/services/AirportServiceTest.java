package com.umakant.airlinebooking.services;


import com.umakant.airlinebooking.dto.AirportDTO;
import com.umakant.airlinebooking.model.Airport;
import com.umakant.airlinebooking.repositories.AirportRepository;
import com.umakant.airlinebooking.services.impl.AirportServicePostgreImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class AirportServiceTest {

    @Mock
    private AirportRepository airportRepository;

//    @Autowired
//    @InjectMocks
//    private AirportService airportService = new AirportServicePostgreImpl(airportRepository);

    @InjectMocks
    private AirportServicePostgreImpl airportServicePostgres;

    @Test
    void contextLoads() throws Exception{
        assertThat(airportServicePostgres).isNotNull();
    }

    @Test
    void isAirportCreated() throws Exception{
//        AirportDTO.NewAirportDTO airport = new AirportDTO.NewAirportDTO(UUID.randomUUID(), "Jaipur International Airport", "26.8283", "75.8060");
//        Airport airportResult = new Airport(airport);
//        airportResult.setAirportName(airportResult.getAirportName().toUpperCase());
//
//        Mockito.when(airportRepository.save(Mockito.any(Airport.class))).thenReturn(airportResult);
//        Airport createdAirport = airportServicePostgres.createAirport(airport);
//        assertThat(createdAirport).isNotNull();
//        assertThat(createdAirport.getAirportName()).isEqualTo(airportResult.getAirportName());
//        assertThat(createdAirport.getLatitude()).isEqualTo(airportResult.getLatitude());
//        assertThat(createdAirport.getLongitude()).isEqualTo(airportResult.getLongitude());
    }

}
