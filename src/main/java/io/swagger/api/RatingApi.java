package io.swagger.api;

import io.swagger.model.Rating;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-01-13T11:54:37.193Z[GMT]")
@Validated
public interface RatingApi {

    @Operation(summary = "Endpoint for get company rating and name", description = "Get rating and company name", tags={  })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rating.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    @RequestMapping(value = "/api/rating",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Rating> getRating(@Parameter(in = ParameterIn.HEADER, description = "Company email for company identification" ,schema=@Schema()) @RequestHeader(value="email", required=true) String email);
}
