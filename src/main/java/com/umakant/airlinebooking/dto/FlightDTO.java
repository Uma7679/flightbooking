package com.umakant.airlinebooking.dto;

import com.umakant.airlinebooking.model.Airport;
import com.umakant.airlinebooking.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class FlightDTO {
    public static class NewFlightDTO{
        public UUID sourceAirportId;
        public List<UUID> stopAirportIds;
        public UUID destinationAirportId;
        public LocalDateTime arrivalTime;
        public LocalDateTime departureTime;
        public int capacity;

        public Flight toFlight(Airport sourceAirport, Airport destinationAirport, List<Airport> stopAirports ){
            return new Flight(null, sourceAirport, stopAirports, destinationAirport, arrivalTime, departureTime, capacity);
        }
    }

    public static class GetFlightDTOResponse{
        public UUID flightId;
        public Airport sourceAirport;
        public Airport destinationAirport;
        public LocalDateTime arrivalTime;
        public LocalDateTime departureTime;

        public GetFlightDTOResponse(UUID flightId, Airport sourceAirport, Airport destinationAirport, LocalDateTime arrivalTime, LocalDateTime departureTime) {
            this.flightId = flightId;
            this.sourceAirport = sourceAirport;
            this.destinationAirport = destinationAirport;
            this.arrivalTime = arrivalTime;
            this.departureTime = departureTime;
        }

        public GetFlightDTOResponse() {
        }

        public static GetFlightDTOResponse getFlightDTO(Flight flight){
            return new GetFlightDTOResponse(flight.getFlightId(), flight.getSourceAirport(), flight.getDestinationAirport(), flight.getArrivalTime(), flight.getDepartureTime());
        }

        public static List<GetFlightDTOResponse> getFlightDTOList(List<Flight> flightList){
            return flightList.stream().map(flight ->
                    new GetFlightDTOResponse(flight.getFlightId(), flight.getSourceAirport(), flight.getDestinationAirport(), flight.getArrivalTime(), flight.getDepartureTime())
            ).toList();
        }
    }

    public static class GetConnectingFlightDTOResponse {
        public GetFlightDTOResponse firstLeg;
        public GetFlightDTOResponse secondLeg;

        public GetConnectingFlightDTOResponse(GetFlightDTOResponse firstLeg, GetFlightDTOResponse secondLeg) {
            this.firstLeg = firstLeg;
            this.secondLeg = secondLeg;
        }
    }

    public static class FlightSearchRequestDTO {

        private FlightDTO.NewFlightDTO flightSearch;

        private int page = 0;
        private int size = 5;
        private String sortBy = "id";
        private boolean ascending = true;

        // Getters and Setters
        public FlightDTO.NewFlightDTO getFlightSearch() {
            return flightSearch;
        }

        public void setFlightSearch(FlightDTO.NewFlightDTO flightSearch) {
            this.flightSearch = flightSearch;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getSortBy() {
            return sortBy;
        }

        public void setSortBy(String sortBy) {
            this.sortBy = sortBy;
        }

        public boolean isAscending() {
            return ascending;
        }

        public void setAscending(boolean ascending) {
            this.ascending = ascending;
        }
    }
}
