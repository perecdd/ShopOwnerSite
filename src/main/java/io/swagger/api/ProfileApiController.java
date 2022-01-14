package io.swagger.api;

import io.swagger.model.Product;
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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
public class ProfileApiController implements ProfileApi {

    private static final Logger log = LoggerFactory.getLogger(ProfileApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ProfileApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Product>> getProfile(@Parameter(in = ParameterIn.HEADER, description = "" ,schema=@Schema()) @RequestHeader(value="companyid", required=false) Integer companyid,@Parameter(in = ParameterIn.HEADER, description = "" ,schema=@Schema()) @RequestHeader(value="password", required=false) String password) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            String result = ShopOwnerSide.getAllProductsByCompanyid(companyid).toString();
            try {
                if (result != null) {
                    return new ResponseEntity<List<Product>>(objectMapper.readValue(result, List.class), HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<List<Product>>(HttpStatus.BAD_REQUEST);
                }
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Product>>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<List<Product>>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> postProfile(@Parameter(in = ParameterIn.HEADER, description = "" ,schema=@Schema()) @RequestHeader(value="companyid", required=false) Integer companyid,@Parameter(in = ParameterIn.HEADER, description = "" ,schema=@Schema()) @RequestHeader(value="password", required=false) String password) {
        String accept = request.getHeader("Accept");
        try {
            if (ShopOwnerSide.registerCompany(companyid, password)) {
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            log.error("Can't register company", e);
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Void> putProfile(@Parameter(in = ParameterIn.HEADER, description = "" ,schema=@Schema()) @RequestHeader(value="companyid", required=false) Integer companyid,@Parameter(in = ParameterIn.HEADER, description = "" ,schema=@Schema()) @RequestHeader(value="password", required=false) String password,@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody ProfileBody body) {
        String accept = request.getHeader("Accept");
        try {
            JSONParser jsonParser = new JSONParser();
            if (ShopOwnerSide.updateProducts(companyid, password, (JSONObject)(jsonParser.parse(body.toString())))) {
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            log.error("Can't register company", e);
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

}
