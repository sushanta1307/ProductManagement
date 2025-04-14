package labs.pm.data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Sushanta Senapati
 * @version 1.0
 * <p>
 * Copyright (c) 2025 Your Company/Name.
 * Licensed under the MIT License.
 * @since 14/04/25
 */
public class ProductManager {
  public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
    return new Food(id, name, price, rating, bestBefore);
  }
  public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
    return new Drink(id, name, price, rating);
  }
}
