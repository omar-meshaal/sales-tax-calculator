package com.ibm.bluemix;

import com.ibm.bluemix.constants.Delimiters;
import com.ibm.bluemix.constants.FileFormats;
import com.ibm.bluemix.values.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 Created by omar on 03-06-2017.
 */

public class ProductLoaderTest {

  private ProductLoader productLoader;

  @Before
  public void setUp() throws Exception {
    productLoader = new ProductLoader();
  }

  private void equals(List<Product> products, int quantity, String name, double price, boolean isImported, int numberOfLines) {
    assertThat(products.get(0).getQuantity(), is(equalTo(quantity)));
    assertThat(products.get(0).getName(), is(equalTo(name)));
    assertThat(products.get(0).getPrice(), is(equalTo(price)));
    assertThat(products.get(0).isImported(), is(isImported));
    assertThat(products.size(), is(equalTo(numberOfLines)));
  }

  @Test
  public void loadProductsFromSpaceFile() throws Exception {
    List<Product> products = productLoader.loadProducts("./data/space.txt", FileFormats.SPACE, Delimiters.SPACE);

    equals(products,1 , "book", 12.49, false, 3);

  }

  @Test
  public void loadProductsFromPipeFile() throws Exception {
    List<Product> products = productLoader.loadProducts("./data/pipe.txt", FileFormats.PIPE, Delimiters.PIPE);
    equals(products,1 , "bottle of perfume", 47.50, true, 2);

  }

  @Test
  public void loadProductsFromCommaFile() throws Exception {
    List<Product> products = productLoader.loadProducts("./data/comma.txt", FileFormats.COMMA, Delimiters.COMMA);
    equals(products,1 , "bottle of perfume", 27.99, true, 4);

  }
  

}
