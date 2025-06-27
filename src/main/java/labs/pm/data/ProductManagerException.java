package labs.pm.data;

/**
 * @author Sushanta Senapati
 * @version 1.0
 * <p>
 * Copyright (c) 2025 Your Company/Name.
 * Licensed under the MIT License.
 * @since 08/05/25
 */
public class ProductManagerException extends Exception {
  ProductManagerException() {
    super();
  }

  ProductManagerException(String msg) {
    super(msg);
  }

  ProductManagerException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
