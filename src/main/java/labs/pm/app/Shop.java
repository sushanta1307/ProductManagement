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

public class Shop {
  public static void main(String[] args) {
    ProductManager pm = new ProductManager();
    Product p1 = pm.createProduct(101, "Maggie",BigDecimal.valueOf(1.99),Rating.THREE_STAR, LocalDate.now().plusDays(2));

    System.out.println(p1);
  }
}