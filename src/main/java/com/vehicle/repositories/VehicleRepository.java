package com.vehicle.repositories;

import com.vehicle.domains.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(value = "SELECT COUNT(*) FROM vehicle WHERE sold = :sold", nativeQuery = true)
    Integer countVehiclesNotSold(@Param("sold") final Boolean sold);

    @Query(value = "SELECT COUNT(*) FROM vehicle WHERE \"year\" = :year", nativeQuery = true)
    Integer countVehiclesByYear(@Param("year") final Integer year);

    @Query(value = "SELECT COUNT(*) FROM vehicle WHERE brand ILIKE :brand", nativeQuery = true)
    Integer countVehiclesByBrand(@Param("brand") final String brand);
}
