package com.vehicle.domains;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.vehicle.constants.VehicleConstants.COLUMN_NAME_YEAR;
import static com.vehicle.constants.VehicleConstants.TABLE_NAME;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = TABLE_NAME)
public class Vehicle extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicle;

    private String brand;

    @Column(name = COLUMN_NAME_YEAR)
    private Integer year;

    private String description;

    private Boolean sold;
}
