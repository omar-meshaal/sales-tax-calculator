package com.ibm.bluemix;

import com.ibm.bluemix.constants.Delimiters;
import com.ibm.bluemix.constants.FileFormats;
import com.ibm.bluemix.values.Product;

import java.io.IOException;
import java.util.List;

/**
 Created by omar on 03-06-2017.
 */
class SalesTaxCalculator {
  private ProductLoader productLoader;
  private TaxCalculator taxCalculator;
  private OutputGenerator outputGenerator;

  private SalesTaxCalculator() {
    this.productLoader = new ProductLoader();
    this.taxCalculator = new TaxCalculator();
    this.outputGenerator =new OutputGenerator();
  }

  void getSalesTax(String filepath, String[] fileFormat, String delimiter, String outputMsg) throws IOException {
    List <Product> products = productLoader.loadProducts(filepath, fileFormat, delimiter);
    List <Product> taxedProducts = taxCalculator.calculateTax(products);
    if(delimiter.equals(Delimiters.SPACE))
      outputGenerator.generateOutput(taxedProducts, Sorter.nameAscending(), outputMsg);
    else
      outputGenerator.generateOutput(taxedProducts, Sorter.ImportedThenPrice(), outputMsg);

  }

  public static void main(String[] args) throws IOException {
    SalesTaxCalculator salesTaxCalculator = new SalesTaxCalculator();

    salesTaxCalculator.getSalesTax("./data/space.txt", FileFormats.SPACE, Delimiters.SPACE,"Output 1:" );
    salesTaxCalculator.getSalesTax("./data/pipe.txt", FileFormats.PIPE, Delimiters.PIPE,"Output 2:" );
    salesTaxCalculator.getSalesTax("./data/comma.txt", FileFormats.COMMA, Delimiters.COMMA,"Output 3:" );


  }

  
}
