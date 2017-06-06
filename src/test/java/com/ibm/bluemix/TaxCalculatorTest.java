package com.ibm.bluemix;

import com.ibm.bluemix.constants.Delimiters;
import com.ibm.bluemix.constants.FileFormats;
import com.ibm.bluemix.values.Product;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 Created by omar on 04-06-2017.
 */
public class TaxCalculatorTest {
  @Test
  public void noTax_for_books_food_medical_Products() {
    List<Product> dummyProducts = new ArrayList<Product>();
    Product product1 = new Product("1 chocolate bar 0.85", FileFormats.SPACE, Delimiters.SPACE);
    Product product2 = new Product("1 packet of headache pills, 9.75", FileFormats.COMMA, Delimiters.COMMA);
    dummyProducts.add(product1);
    dummyProducts.add(product2);
    TaxCalculator taxCalculator = new TaxCalculator();
    List<Product> taxedProducts = taxCalculator.calculateTax(dummyProducts);
    Assert.assertEquals(0.85, taxedProducts.get(0).getPrice(), 0.01);
    Assert.assertEquals(9.75, taxedProducts.get(1).getPrice(), 0.01);
  }

  @Test
  public void tenPrencent_Tax_other_Products() {
    List<Product> dummyProducts = new ArrayList<Product>();
    Product product1 = new Product("1 music CD 14.99", FileFormats.SPACE, Delimiters.SPACE);
    Product product2 = new Product("1 bottle of perfume, 18.99", FileFormats.COMMA, Delimiters.COMMA);
    dummyProducts.add(product1);
    dummyProducts.add(product2);
    TaxCalculator taxCalculator = new TaxCalculator();
    List<Product> taxedProducts = taxCalculator.calculateTax(dummyProducts);
    Assert.assertEquals(16.49, taxedProducts.get(0).getPrice(), 0.01);
    Assert.assertEquals(20.89, taxedProducts.get(1).getPrice(), 0.01);
  }
  @Ignore
  @Test
  public void fivePrencent_Tax_for_imported_Products() {
    List<Product> dummyProducts = new ArrayList<Product>();
    Product product1 = new Product("Imported | 1 box of chocolates | 10.00", FileFormats.PIPE, Delimiters.PIPE);
    Product product2 = new Product("1 box of chocolates, 11.25, imported", FileFormats.COMMA, Delimiters.COMMA);
    dummyProducts.add(product1);
    dummyProducts.add(product2);
    TaxCalculator taxCalculator = new TaxCalculator();
    List<Product> taxedProducts = taxCalculator.calculateTax(dummyProducts);
    Assert.assertEquals(10.50, taxedProducts.get(0).getPrice(), 0.01);
    Assert.assertEquals(11.85, taxedProducts.get(1).getPrice(), 0.01);
  }


  @Test
  public void fifteenPrencent_Tax_for_imported_Other_Products() {
    List<Product> dummyProducts = new ArrayList<Product>();
    Product product1 = new Product("Imported | 1 bottle of perfume | 47.50", FileFormats.PIPE, Delimiters.PIPE);
    dummyProducts.add(product1);
    TaxCalculator taxCalculator = new TaxCalculator();
    List<Product> taxedProducts = taxCalculator.calculateTax(dummyProducts);
    Assert.assertEquals(54.65, taxedProducts.get(0).getPrice(), 0.01);
  }

}
