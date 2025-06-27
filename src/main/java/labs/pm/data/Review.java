package labs.pm.data;

/**
 * @author Sushanta Senapati
 * @version 1.0
 * <p>
 * Copyright (c) 2025 Your Company/Name.
 * Licensed under the MIT License.
 * @since 16/04/25
 */
public class Review implements Comparable<Review>{
  private Rating rating;
  private String comments;

  public Review(Rating rating, String comments) {
    this.rating = rating;
    this.comments = comments;
  }

  @Override
  public int compareTo(Review other) {
    // Decreasing order of ratings
    return other.rating.ordinal() - this.rating.ordinal();
  }

  public Rating getRating() {
    return rating;
  }

  public String getComments() {
    return comments;
  }

  @Override
  public String toString() {
    return "Review{" +
        "rating=" + rating +
        ", comments='" + comments + '\'' +
        '}';
  }
}
