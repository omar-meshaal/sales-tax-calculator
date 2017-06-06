package com.ibm.bluemix;

import com.ibm.bluemix.constants.Taxes;
import com.ibm.bluemix.values.Product;

import java.util.List;

/**
 Created by omar on 03-06-2017.
 */
class TaxCalculator {

  private static final int fivePercentRounding = 20;

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
      return ((double) Math.round((Taxes.BASIC_TAX + Taxes.IMPORT_TAX) * product.getPrice() * fivePercentRounding) / fivePercentRounding);

    if(product.isImported())
      return  ((double) Math.round(Taxes.IMPORT_TAX * product.getPrice() * fivePercentRounding) / fivePercentRounding);

    if(isApplicableForBasicTax(product))
      return  ((double) Math.round(Taxes.BASIC_TAX * product.getPrice() * fivePercentRounding) / fivePercentRounding);

    return 0.00;
  }

  private boolean isApplicableForBasicTax(Product product) {
    return product.getCategory().equals("Other");
  }
}
