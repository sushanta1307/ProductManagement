package labs.pm.data;

/**
 * @author Sushanta Senapati
 * @version 1.0
 * <p>
 * Copyright (c) 2025 Your Company/Name.
 * Licensed under the MIT License.
 * @since 16/04/25
 */

@FunctionalInterface // allows only single abstract method inside the interface
public interface Rateable<T> {
  public static final Rating DEFAULT_RATING = Rating.NO_STAR;

  public abstract T applyRating(Rating rating);

  public default Rating getRating() {
    return DEFAULT_RATING;
  }

  public static Rating convert(int stars) {
    return (stars>=0 && stars<=5) ? Rating.values()[stars] : DEFAULT_RATING;
  }

  public default T applyRating(int stars) {
    return applyRating(convert(stars));
  }
}
