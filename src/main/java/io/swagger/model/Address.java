package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Address
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-01-13T11:54:37.193Z[GMT]")


public class Address   {
  @JsonProperty("city")
  private String city = null;

  @JsonProperty("country")
  private String country = null;

  @JsonProperty("flat")
  private String flat = null;

  @JsonProperty("house")
  private String house = null;

  @JsonProperty("street")
  private String street = null;

  public Address city(String city) {
    this.city = city;
    return this;
  }

  /**
   * Get city
   * @return city
   **/
  @Schema(description = "")
  
    public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Address country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
   **/
  @Schema(description = "")
  
    public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Address flat(String flat) {
    this.flat = flat;
    return this;
  }

  /**
   * Get flat
   * @return flat
   **/
  @Schema(description = "")
  
    public String getFlat() {
    return flat;
  }

  public void setFlat(String flat) {
    this.flat = flat;
  }

  public Address house(String house) {
    this.house = house;
    return this;
  }

  /**
   * Get house
   * @return house
   **/
  @Schema(description = "")
  
    public String getHouse() {
    return house;
  }

  public void setHouse(String house) {
    this.house = house;
  }

  public Address street(String street) {
    this.street = street;
    return this;
  }

  /**
   * Get street
   * @return street
   **/
  @Schema(description = "")
  
    public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(this.city, address.city) &&
        Objects.equals(this.country, address.country) &&
        Objects.equals(this.flat, address.flat) &&
        Objects.equals(this.house, address.house) &&
        Objects.equals(this.street, address.street);
  }

  @Override
  public int hashCode() {
    return Objects.hash(city, country, flat, house, street);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");
    
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    flat: ").append(toIndentedString(flat)).append("\n");
    sb.append("    house: ").append(toIndentedString(house)).append("\n");
    sb.append("    street: ").append(toIndentedString(street)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
