package com.ibm.bluemix;

import com.ibm.bluemix.values.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 Created by omar on 03-06-2017.
 */
class ProductLoader {

  List<Product> loadProducts(String filepath, String[] fileFormat, String delimiter) throws IOException {
    List<Product> products = new ArrayList<Product>();
    BufferedReader br = new BufferedReader(new FileReader(filepath));
      String sCurrentLine;
      while((sCurrentLine = br.readLine()) != null) {
        Product product = new Product(sCurrentLine, fileFormat, delimiter);
        products.add(product);
      }

    return products;
  }
  
}
