package com.ibm.bluemix.values;

import com.ibm.bluemix.constants.Delimiters;
import com.ibm.bluemix.constants.FileFormats;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 Created by omar on 04-06-2017.
 */
public class ProductTest {

  private void equals(Product product1, int quantity, String productName, double price, boolean isImported) {
    assertEquals("parse product quantity", quantity, product1.getQuantity());
    assertEquals("parse product description", productName, product1.getName());
    assertEquals("parse price", price, product1.getPrice(), .01);
    assertEquals("check if imported", isImported, product1.isImported());
  }

  @Test
  public void test_space_delimited_records() {
    String line1 = "1 book 12.49";
    Product product1 = new Product(line1, FileFormats.SPACE, Delimiters.SPACE);
    equals(product1,1 , "book", 12.49, false);

    String line2 = "1 music CD 14.99";
    Product product2 = new Product(line2, FileFormats.SPACE, Delimiters.SPACE);
    equals(product2,1 , "music CD", 14.99, false);
  }


  @Test
  public void test_pipe_delimited_records() {
    String line1 = "Imported | 1 bottle of perfume | 47.50";
    Product product1 = new Product(line1, FileFormats.PIPE, Delimiters.PIPE);
    equals(product1,1 , "bottle of perfume", 47.50, true);
  }

  @Test
  public void test_comma_delimited_records() {
    String line1 = "1 bottle of perfume, 27.99, imported";
    Product product1 = new Product(line1, FileFormats.COMMA, Delimiters.COMMA);
    equals(product1,1 , "bottle of perfume", 27.99, true);

    String line2 = "1 packet of headache pills, 9.75";
    Product product2 = new Product(line2, FileFormats.COMMA, Delimiters.COMMA);
    equals(product2,1 , "packet of headache pills", 9.75, false);

  }
}
