package com.vehicle.repositories;

import com.vehicle.domains.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT COUNT(v) FROM Vehicle v WHERE v.sold = :sold")
    Integer countVehiclesNotSold(@Param("sold") final Boolean sold);

    @Query("SELECT COUNT(v) FROM Vehicle v WHERE v.year = :year")
    Integer countVehiclesByYear(@Param("year") final Integer year);

    @Query("SELECT COUNT(v) FROM Vehicle v WHERE LOWER(v.brand) = LOWER(:brand)")
    Integer countVehiclesByBrand(@Param("brand") final String brand);

    Page<Vehicle> findByVehicleIgnoreCaseAndCreatedAtAfter(final String vehicle, final LocalDateTime createdAt, final Pageable pageable);
}
