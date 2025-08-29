package com.umakant.airlinebooking.repositories;

import com.umakant.airlinebooking.model.AirlineUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AirlineUserRepository extends JpaRepository<AirlineUser, UUID> {
    AirlineUser getUserByUserId(UUID airlineUserId);
    boolean existsByUserId(UUID airlineUserId);
}
