/**
 * @author Sushanta Senapati
 * @version 1.0
 * <p>
 * Copyright (c) 2025 Your Company/Name.
 * Licensed under the MIT License.
 * @since 12/04/25
 */

package labs.pm.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Product implements Rateable<Product>{
  private int id;
  private String name;
  private BigDecimal price;
  private static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);
  private Rating rating;

  Product(int id, String name, BigDecimal price, Rating rating) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.rating = rating;
  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(final BigDecimal price) {
    this.price = price;
  }

  /**
   * Calculates the discount for the product
   * {@link DISCOUNT_RATE discount rate}
   * @return a {@link java.math.BigDecimal BigDecimal}
   * value of the discount
   */
  public BigDecimal getDiscount() {
    return price.multiply(DISCOUNT_RATE).setScale(2, RoundingMode.HALF_UP);
  }

  @Override
  public Rating getRating() {
    return rating;
  }

  public void setRating(Rating rating) {
    this.rating = rating;
  }

  public LocalDate getBestBefore() {
    return LocalDate.now();
  }

  @Override
  public String toString() {
    return "Product: " +
        "id=" + id +
        ", name='" + name + '\'' +
        ", price=" + price +
        ", rating=" + rating.getStars()
        + " ";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Product)) return false;
    Product product = (Product) obj;
    return id == product.id && Objects.equals(name, product.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, price, rating);
  }
}
