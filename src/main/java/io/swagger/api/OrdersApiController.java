package io.swagger.api;

import io.swagger.model.Order;
import io.swagger.model.OrdersBody;
import io.swagger.model.ProfileBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-01-13T11:54:37.193Z[GMT]")
@RestController
public class OrdersApiController implements OrdersApi {

    private static final Logger log = LoggerFactory.getLogger(OrdersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public OrdersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Order>> getOrders(@Parameter(in = ParameterIn.HEADER, description = "The email address of the registered company for its identification in the database." ,schema=@Schema()) @RequestHeader(value="email", required=true) String email, @Parameter(in = ParameterIn.HEADER, description = "Company password" ,schema=@Schema()) @RequestHeader(value="password", required=true) String password) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            String result = ShopOwnerSide.getTickets(email, password).toString();
            try {
                if(result != null) {
                    return new ResponseEntity<List<Order>>(objectMapper.readValue(result, List.class), HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<List<Order>>(HttpStatus.UNAUTHORIZED);
                }
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Order>>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<List<Order>>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> putOrders(@Parameter(in = ParameterIn.HEADER, description = "The email address of the registered company for its identification in the database." ,schema=@Schema()) @RequestHeader(value="email", required=true) String email, @Parameter(in = ParameterIn.HEADER, description = "Company password." ,schema=@Schema()) @RequestHeader(value="password", required=true) String password, @Parameter(in = ParameterIn.HEADER, description = "Order ID." ,schema=@Schema()) @RequestHeader(value="index", required=true) Integer index, @Parameter(in = ParameterIn.HEADER, description = "New status for this order." ,schema=@Schema()) @RequestHeader(value="status", required=true) String status) {
        String accept = request.getHeader("Accept");
        try {
            if (ShopOwnerSide.updateTicket(email, password, index, status)) {
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
            }
        }
        catch (Exception e){
            log.error("Can't update ticket on ShopOwnerSide", e);
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }
}
