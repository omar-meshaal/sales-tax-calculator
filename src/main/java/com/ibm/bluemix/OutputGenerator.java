package com.ibm.bluemix;

import com.ibm.bluemix.values.Product;

import java.util.Comparator;
import java.util.List;

/**
 Created by omar on 03-06-2017.
 */
class OutputGenerator {
  void generateOutput(List<Product> taxedProducts, Comparator<Product> comparator, String outputMsg) {
    double salesTaxes = 0;
    double total = 0;

    taxedProducts.sort(comparator);
    System.out.println(outputMsg);

    for(Product product : taxedProducts) {
      salesTaxes += product.getTax();
      total +=product.getPrice();
      System.out.println(product);
    }
    System.out.println("\nSales Taxes: "+String.format("%.2f", salesTaxes));
    System.out.println("Total: "+String.format("%.2f", total)+"\n\n");
  }
}
