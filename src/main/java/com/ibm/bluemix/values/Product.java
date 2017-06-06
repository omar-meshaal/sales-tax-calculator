package com.ibm.bluemix.values;

import com.ibm.bluemix.constants.Delimiters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Created by omar on 03-06-2017.
 */
public class Product {
  public static final String DESCRIPTION = "description";
  public static final String PRICE = "price";
  public static final String IS_IMPORTED = "isImported";

  private int quantity;
  private String name;
  private double price;
  private double tax;
  private boolean isImported;

  public Product(String line, String[] lineElements, String delimiter) {
    List<String> tokens = new ArrayList<String>();
    if(delimiter.equalsIgnoreCase(Delimiters.SPACE)) {
      tokens.add(line.substring(0, line.lastIndexOf(" ")));
      tokens.add(line.substring(line.lastIndexOf(" ") + 1));
    }
    else {
      tokens = Arrays.asList(line.split(delimiter));
    }

    int i = 0;
    for(String token : tokens) {
      switch(lineElements[i++]) {

        case DESCRIPTION:
          name = token.substring(2);
          quantity = Integer.parseInt(token.substring(0, token.indexOf(" ")));
          break;

        case PRICE:
          price = Double.parseDouble(token);
          break;

        case IS_IMPORTED:
          isImported = token.equalsIgnoreCase("Imported");
          break;


        default:
          break;

      }
    }
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  public boolean isImported() {
    return isImported;
  }

  public int getQuantity() {
    return quantity;
  }


  public double getTax() {
    return tax;
  }

  public void setTax(double tax) {
    this.tax = tax;
  }

  public String getCategory() {
    if(this.name.contains("book")) { return "Books"; }
    else if(this.name.contains("chocolate")) { return "Food"; }
    else if(this.name.contains("headache pill")) { return "Medical"; }
    return "Other";
  }

  @Override
  public String toString() {
    String isImported = this.isImported? " imported" : "";
    return quantity + isImported+ " " + name + ": " + String.format("%.2f", price);
  }
}
