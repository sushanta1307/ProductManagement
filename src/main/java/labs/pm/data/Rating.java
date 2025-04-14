package labs.pm.data;

/**
 * @author Sushanta Senapati
 * @version 1.0
 * <p>
 * Copyright (c) 2025 Your Company/Name.
 * Licensed under the MIT License.
 * @since 13/04/25
 */
public enum Rating {
  NO_STAR("\u2606\u2606\u2606\u2606\u2606"),
  ONE_STAR("\u2605\u2606\u2606\u2606\u2606"),
  TWO_STAR("\u2605\u2605\u2606\u2606\u2606"),
  THREE_STAR("\u2605\u2605\u2605\u2606\u2606"),
  FOUR_STAR("\u2605\u2605\u2605\u2605\u2606"),
  FIVE_STAR("\u2605\u2605\u2605\u2605\u2605");

  private String stars;

  Rating(String stars) {
    this.stars = stars;
  }

  public String getStars() {
    return stars;
  }
}
