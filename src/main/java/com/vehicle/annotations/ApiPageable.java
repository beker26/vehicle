package com.vehicle.annotations;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.vehicle.constants.AnnotationsConstants.DESCRIPTION_PAGE;
import static com.vehicle.constants.AnnotationsConstants.DESCRIPTION_SIZE;
import static com.vehicle.constants.AnnotationsConstants.DESCRIPTION_SORT;
import static com.vehicle.constants.AnnotationsConstants.EXAMPLE_ASC;
import static com.vehicle.constants.AnnotationsConstants.EXAMPLE_TEN;
import static com.vehicle.constants.AnnotationsConstants.EXAMPLE_ZERO;
import static com.vehicle.constants.AnnotationsConstants.NAME_PAGE;
import static com.vehicle.constants.AnnotationsConstants.NAME_SIZE;
import static com.vehicle.constants.AnnotationsConstants.NAME_SORT;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Parameters({
        @Parameter(name = NAME_PAGE, description = DESCRIPTION_PAGE, example = EXAMPLE_ZERO),
        @Parameter(name = NAME_SIZE, description = DESCRIPTION_SIZE, example = EXAMPLE_TEN),
        @Parameter(name = NAME_SORT, description = DESCRIPTION_SORT, example = EXAMPLE_ASC)
})
public @interface ApiPageable {
}
