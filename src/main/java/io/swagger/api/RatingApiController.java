package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Rating;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-01-13T11:54:37.193Z[GMT]")
@RestController
public class RatingApiController implements RatingApi {

    private static final Logger log = LoggerFactory.getLogger(ProfileApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public RatingApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Rating> getRating(@Parameter(in = ParameterIn.HEADER, description = "Company email for company identification" ,schema=@Schema()) @RequestHeader(value="email", required=true) String email){
        String accept = request.getHeader("Accept");
        String result = ShopOwnerSide.getRating(email).toString();
        if (result != null) {
            try {
                return new ResponseEntity<Rating>(objectMapper.readValue(result, Rating.class), HttpStatus.OK);
            }
            catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<Rating>(HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<Rating>(HttpStatus.BAD_REQUEST);
        }
    }

}
