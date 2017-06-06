package com.ibm.bluemix.constants;

import com.ibm.bluemix.values.Product;

/**
 Created by omar on 04-06-2017.
 */
public class FileFormats {
  public static final String[] SPACE = new String[] {Product.DESCRIPTION, Product.PRICE};
  public static final String[] PIPE = new String[] {Product.IS_IMPORTED, Product.DESCRIPTION, Product.PRICE};
  public static final String[] COMMA = new String[] {Product.DESCRIPTION, Product.PRICE, Product.IS_IMPORTED};
}
