package org.jala.foundation.dev32.jwt.example.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

  @NotBlank
  private String name;
  @Min(0)
  private Float price;

  public ProductDto() {
  }

  public ProductDto(@NotBlank String name, @Min(0) Float price) {
    this.name = name;
    this.price = price;
  }
}
