package labs.pm.data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Sushanta Senapati
 * @version 1.0
 * <p>
 * Copyright (c) 2025 Your Company/Name.
 * Licensed under the MIT License.
 * @since 13/04/25
 */

// final class makes it the leaf class. It can;t be extended
public final class Food extends Product{
  private LocalDate bestBefore;

  Food(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
    super(id, name, price, rating);
    this.bestBefore = bestBefore;
  }

  public LocalDate getBestBefore() {
    return bestBefore;
  }

  @Override
  public String toString() {
    return super.toString() +
        "bestBefore=" + bestBefore;
  }

  @Override
  public BigDecimal getDiscount() {
    return this.bestBefore.isAfter(LocalDate.now()) ? super.getDiscount() : BigDecimal.valueOf(1);
  }

  @Override
  public Product applyRating(Rating newRating) {
    return new Food(getId(), getName(), getPrice(), newRating, getBestBefore());
  }
}
