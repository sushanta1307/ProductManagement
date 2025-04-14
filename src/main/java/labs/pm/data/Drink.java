package labs.pm.data;

import java.math.BigDecimal;

/**
 * @author Sushanta Senapati
 * @version 1.0
 * <p>
 * Copyright (c) 2025 Your Company/Name.
 * Licensed under the MIT License.
 * @since 13/04/25
 */
public class Drink extends Product{
  Drink(int id, String name, BigDecimal price, Rating rating) {
    super(id, name, price, rating);
  }
  @Override
  public Product applyRating(Rating newRating) {
    throw new UnsupportedOperationException("Not supported yet");
  }
}
