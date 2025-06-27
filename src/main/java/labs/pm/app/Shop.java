/**
 * @author Sushanta Senapati
 * @version 1.0
 * <p>
 * Copyright (c) 2025 Your Company/Name.
 * Licensed under the MIT License.
 * @since 12/04/25
 */

package labs.pm.app;

import labs.pm.data.Product;
import labs.pm.data.ProductManager;
import labs.pm.data.Rating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Locale;

public class Shop {
  public static void main(String[] args) {
    ProductManager pm = new ProductManager(Locale.FRANCE);
    pm.createProduct(101, "Maggie",BigDecimal.valueOf(1.99),Rating.ONE_STAR, LocalDate.now().plusDays(2));
    pm.reviewProduct(101, Rating.ONE_STAR, "Ridiculous");
    pm.reviewProduct(101, Rating.THREE_STAR, "Terrific purchase");
//    pm.printProductReport(p1);

    pm.createProduct(102, "Chocolate",BigDecimal.valueOf(2.99),Rating.THREE_STAR, LocalDate.now().plusDays(2));
    pm.reviewProduct(102, Rating.THREE_STAR, "Awesome");
//    pm.printProductReport(p2);
    pm.parseReview("101,4,Nice cup of Tea");

    Comparator<Product> ratingSorter = (a, b) -> b.getRating().ordinal() - a.getRating().ordinal();
    Comparator<Product> priceSorter = (a, b) -> b.getPrice().compareTo(a.getPrice());

    pm.printProducts(f -> true, priceSorter.thenComparing(ratingSorter).reversed());

    pm.printProductReport(101);
    pm.printProductReport(42);

    pm.getDiscounts().forEach(
        (rating, discount) -> System.out.println(rating + "\t" + discount)
    );
  }
}