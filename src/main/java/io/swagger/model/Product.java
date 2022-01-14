package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Product
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-01-13T11:54:37.193Z[GMT]")


public class Product   {
  @JsonProperty("Photo")
  private String photo = null;

  @JsonProperty("companyid")
  private Integer companyid = null;

  @JsonProperty("count")
  private Integer count = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("price")
  private Integer price = null;

  @JsonProperty("productid")
  private Integer productid = null;

  public Product photo(String photo) {
    this.photo = photo;
    return this;
  }

  /**
   * Get photo
   * @return photo
   **/
  @Schema(description = "")
  
    public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public Product companyid(Integer companyid) {
    this.companyid = companyid;
    return this;
  }

  /**
   * Get companyid
   * @return companyid
   **/
  @Schema(description = "")
  
    public Integer getCompanyid() {
    return companyid;
  }

  public void setCompanyid(Integer companyid) {
    this.companyid = companyid;
  }

  public Product count(Integer count) {
    this.count = count;
    return this;
  }

  /**
   * Get count
   * @return count
   **/
  @Schema(description = "")
  
    public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Product description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @Schema(description = "")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Product name(String name) {
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

  public Product price(Integer price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
   **/
  @Schema(description = "")
  
    public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Product productid(Integer productid) {
    this.productid = productid;
    return this;
  }

  /**
   * Get productid
   * @return productid
   **/
  @Schema(description = "")
  
    public Integer getProductid() {
    return productid;
  }

  public void setProductid(Integer productid) {
    this.productid = productid;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(this.photo, product.photo) &&
        Objects.equals(this.companyid, product.companyid) &&
        Objects.equals(this.count, product.count) &&
        Objects.equals(this.description, product.description) &&
        Objects.equals(this.name, product.name) &&
        Objects.equals(this.price, product.price) &&
        Objects.equals(this.productid, product.productid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(photo, companyid, count, description, name, price, productid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Product {\n");
    
    sb.append("    photo: ").append(toIndentedString(photo)).append("\n");
    sb.append("    companyid: ").append(toIndentedString(companyid)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    productid: ").append(toIndentedString(productid)).append("\n");
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
