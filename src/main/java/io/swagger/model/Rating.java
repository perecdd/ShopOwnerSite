package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * InlineResponse200
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-12-19T09:20:14.611Z[GMT]")


public class Rating {
    @JsonProperty("name")
    @Valid
    private String name = new String();

    public Rating name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get rating
     * @return rating
     **/
    @Schema(required = true, description = "")
    @NotNull
    @Valid
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    @JsonProperty("rating")
    @Valid
    private Double rating = new Double(0);

    public Rating rating(Double rating) {
        this.rating = rating;
        return this;
    }

    /**
     * Get rating
     * @return rating
     **/
    @Schema(required = true, description = "")
    @NotNull
    @Valid
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rating object = (Rating) o;
        return Objects.equals(this.rating, object.rating) && Objects.equals(this.name, object.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rating, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Rating {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

