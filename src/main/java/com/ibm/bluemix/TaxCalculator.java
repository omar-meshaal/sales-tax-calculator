package com.ibm.bluemix;

import com.ibm.bluemix.values.Product;

import java.util.List;

/**
 Created by omar on 03-06-2017.
 */
class TaxCalculator {
  List<Product> calculateTax(List<Product> products) {
    for(Product product : products){
      double tax = getTax(product);
      product.setTax(tax);
      product.setPrice(product.getPrice() + tax);
    }
    return products;
  }

  private double getTax(Product product) {
    if(isApplicableForBasicTax(product) && product.isImported())
      return ((double) Math.round((double)0.15 * product.getPrice() * 20) / 20);

    if(product.isImported())
      return  ((double) Math.round((double)0.05 * product.getPrice() * 20) / 20);

    if(isApplicableForBasicTax(product))
      return  ((double) Math.round((double)0.1 * product.getPrice() * 20) / 20);

    return 0.00;
  }

  private boolean isApplicableForBasicTax(Product product) {
    return product.getCategory().equals("Other");
  }
}
