package com.ibm.bluemix;

import com.ibm.bluemix.values.Product;

import java.util.Comparator;

/**
 Created by omar on 05-06-2017.
 */
class Sorter {
  static Comparator<Product> nameAscending() {
    return (Product p1, Product p2) -> p1.getName().compareTo(p2.getName());
  }
  
  static Comparator<Product> ImportedThenPrice() {
    return (Product p1, Product p2) -> {
      if(((Boolean) p1.isImported()).compareTo((Boolean) p2.isImported()) != 0)
       return ((Boolean) p1.isImported()).compareTo((Boolean) p2.isImported());

      return ((Double) p1.getPrice()).compareTo((Double) p2.getPrice());
    };
  }
  
}
