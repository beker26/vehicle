package com.vehicle.annotations;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Parameters({
        @Parameter(name = "page", description = "Page number (starts from 0)", example = "0"),
        @Parameter(name = "size", description = "Page size", example = "10"),
        @Parameter(name = "sort", description = "Sorting criteria in the format: property(,asc|desc).", example = "name,asc")
})
public @interface ApiPageable {
}
