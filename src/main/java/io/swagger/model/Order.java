package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Address;
import io.swagger.model.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Order
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-01-13T11:54:37.193Z[GMT]")


public class Order   {
  @JsonProperty("address")
  private Address address = null;

  @JsonProperty("products")
  @Valid
  private List<Product> products = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("phone")
  private String phone = null;

  @JsonProperty("surname")
  private String surname = null;

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("status")
  private String status = null;

  public Order address(Address address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
   **/
  @Schema(description = "")
  
    @Valid
    public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Order products(List<Product> products) {
    this.products = products;
    return this;
  }

  public Order addProductsItem(Product productsItem) {
    if (this.products == null) {
      this.products = new ArrayList<Product>();
    }
    this.products.add(productsItem);
    return this;
  }

  /**
   * Get products
   * @return products
   **/
  @Schema(description = "")
      @Valid
    public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public Order email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   **/
  @Schema(description = "")
  
    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Order name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(description = "")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Order phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * Get phone
   * @return phone
   **/
  @Schema(description = "")
  
    public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Order surname(String surname) {
    this.surname = surname;
    return this;
  }

  /**
   * Get surname
   * @return surname
   **/
  @Schema(description = "")
  
    public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public Order id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(description = "")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Order status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   **/
  @Schema(description = "")
  
    public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(this.address, order.address) &&
        Objects.equals(this.products, order.products) &&
        Objects.equals(this.email, order.email) &&
        Objects.equals(this.name, order.name) &&
        Objects.equals(this.phone, order.phone) &&
        Objects.equals(this.surname, order.surname) &&
        Objects.equals(this.id, order.id) &&
        Objects.equals(this.status, order.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, products, email, name, phone, surname, id, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");
    
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    products: ").append(toIndentedString(products)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    surname: ").append(toIndentedString(surname)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
