package labs.pm.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.format.FormatStyle;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Sushanta Senapati
 * @version 1.0
 * <p>
 * Copyright (c) 2025 Your Company/Name.
 * Licensed under the MIT License.
 * @since 14/04/25
 */
public class ProductManager {

  private static class ResourceFormatter {
    private Locale locale;
    private ResourceBundle resources;
    private DateTimeFormatter dateFormat;
    protected NumberFormat moneyFormat;
    private ResourceBundle config = ResourceBundle.getBundle("labs.pm.data.config");
    private MessageFormat reviewFormat = new MessageFormat(config.getString("review.data.format"));
    private MessageFormat productFromat = new MessageFormat(config.getString("product.data.format"));

    private ResourceFormatter(Locale locale) {
      this.locale = locale;
      resources = ResourceBundle.getBundle("labs.pm.data.resources",locale);
      dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).localizedBy(locale);
      moneyFormat = NumberFormat.getCurrencyInstance(locale);
    }

    private String formatProduct(Product product) {
      return MessageFormat.format(resources.getString("product"),
          product.getName(),
          moneyFormat.format(product.getPrice()),
          product.getRating().getStars(),
          dateFormat.format(product.getBestBefore()));
    }

    private String formatReview(Review review) {
      return MessageFormat.format(resources.getString("review"),
          review.getRating().getStars(),
          review.getComments());
    }

    private String getText(String key) {
      return resources.getString(key);
    }
  }
  private Map<Product, List<Review>> products = new HashMap<>();
  private ResourceFormatter formatter;
  private static Map<String, ResourceFormatter> formatters =
      Map.of("en-GB", new ResourceFormatter(Locale.UK),
             "en-US", new ResourceFormatter(Locale.US),
             "fr-FR", new ResourceFormatter(Locale.FRANCE));

  public ProductManager(Locale locale) {
    this(locale.toLanguageTag());
  }

  public ProductManager(String languageTag) {
    changeLocale(languageTag);
  }
  public void changeLocale(String languageTag) {
    formatter = formatters.getOrDefault(languageTag, formatters.get("en-GB"));
  }

  public static Set<String> getSupportedLocales() {
    return formatters.keySet();
  }

  public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
    Product product = new Food(id, name, price, rating, bestBefore);
    products.putIfAbsent(product, new ArrayList<>());
    return product;
  }
  public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
    Product product = new Drink(id, name, price, rating);
    products.putIfAbsent(product, new ArrayList<>());
    return product;
  }

  public Product reviewProduct(int id, Rating rating, String comments) {
    try {
      Product product = findProduct(id);
      List<Review> reviews = products.get(product);
      products.remove(product);
      reviews.add(new Review(rating, comments));
      //    int sum = 0;
      //    for(Review review: reviews) {
      //      sum += review.getRating().ordinal();
      //    }
      //    product = product.applyRating(Rateable.convert(Math.round((float)sum/reviews.size())));
      product.applyRating(
          Rateable.convert(
              (int) Math.round(reviews.stream()
                  .mapToInt(review -> review.getRating().ordinal())
                  .average()
                  .orElse(0))));
      products.put(product, reviews);
      return product;
    } catch (ProductManagerException ex) {
      Logger.getLogger(ProductManager.class.getName())
          .log(Level.SEVERE, null, ex);
      return null;
    }
  }

  public void printProductReport(int id) {
    try {
      StringBuilder txt = new StringBuilder();
      Product product = findProduct(id);
      txt.append(formatter.formatProduct(product));
      txt.append("\n");
      List<Review> reviews = products.get(product);
      Collections.sort(reviews);
      //    if(reviews.isEmpty()) {
      //      txt.append(formatter.getText("no.reviews"));
      //      txt.append("\n");
      //    }
      //    for(Review review: reviews) {
      //      txt.append(formatter.formatReview(review));
      //      txt.append("\n");
      //    }
      if (reviews.isEmpty()) {
        txt.append(formatter.getText("no.reviews") + '\n');
      } else {
        //      txt.append(reviews.stream()
        //          .map(review -> formatter.formatReview(review) + '\n')
        //          .collect(Collectors.joining()));
        reviews.stream()
            .forEach(review -> txt.append(formatter.formatReview(review) + '\n'));
      }
      System.out.println(txt);
    } catch (ProductManagerException ex) {
      Logger.getLogger(ProductManager.class.getName())
          .log(Level.SEVERE, null, ex);
    }
  }

  public void printProducts(Predicate<Product> filter,Comparator<Product> sorter) {
//    List<Product> productList = new ArrayList<>(products.keySet());
//    productList.sort(sorter);
    StringBuilder txt = new StringBuilder();
//    for(Product product: productList) {
//      txt.append(formatter.formatProduct(product));
//      txt.append("\n");
//    }
    products.keySet()
        .stream()
        .sorted(sorter)
        .filter(filter)
        .forEach(product -> txt.append(formatter.formatProduct(product) + '\n'));
    System.out.println(txt);
  }
  public void parseReview(String text) {
    try {
      Object[] values = formatter.reviewFormat.parse(text);
      reviewProduct(Integer.parseInt((String) values[0]),
          Rateable.convert(Integer.parseInt((String)values[1])),
          (String) values[2]);
    } catch (ParseException e) {
      Logger.getLogger(ProductManager.class.getName())
          .log(Level.WARNING, "Error parsing review " + text, e);
    }
  }

  public Map<String, String> getDiscounts() {
    return products.keySet()
        .stream()
        .collect(
            Collectors.groupingBy(
                product -> product.getRating().getStars(),
                Collectors.collectingAndThen(
                    Collectors.summingDouble(
                        product -> product.getDiscount().doubleValue()),
                        discount -> formatter.moneyFormat.format(discount))));
  }
  public Product findProduct(int id) throws ProductManagerException {
//    Product result = null;
//    for(Product product: products.keySet()) {
//      if(product.getId() == id) {
//        result = product;
//        break;
//      }
//    }
//    return result;
    return products.keySet()
        .stream()
        .filter(product -> product.getId() == id)
        .findFirst()
        .orElseThrow(() -> new ProductManagerException("Id " + id + " not found"));
  }
}
