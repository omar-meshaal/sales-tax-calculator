package com.ibm.bluemix;

import com.ibm.bluemix.constants.Delimiters;
import com.ibm.bluemix.constants.FileFormats;
import com.ibm.bluemix.values.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SalesTaxCalculatorTest{
  @InjectMocks
  private SalesTaxCalculator subject;

  @Mock
  private ProductLoader productloader;

  @Mock
  private TaxCalculator taxCalculator;

  @Mock
  private OutputGenerator outputGenerator;

  @Test
  public void call_GenerateOutput() throws Exception {
    List<Product> dummyProducts = Collections.singletonList(new Product("1 book 12.49", FileFormats.SPACE, Delimiters.SPACE));
    List<Product> TaxedProducts = Collections.singletonList(new Product("1 book 12.49", FileFormats.SPACE, Delimiters.SPACE));
    String  filepath = "";
    when(productloader.loadProducts(filepath, FileFormats.SPACE, Delimiters.SPACE)).thenReturn(dummyProducts);
    when(taxCalculator.calculateTax(dummyProducts)).thenReturn(TaxedProducts);

    subject.getSalesTax(filepath, FileFormats.SPACE, Delimiters.SPACE, "");
    verify(outputGenerator).generateOutput(TaxedProducts,Sorter.nameAscending() ,"" );
  }
  @Test
  public void outputTaxedProducts() throws IOException {
    ByteArrayOutputStream systemOutStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(systemOutStream));

    SalesTaxCalculator.main(null);

    List<String> expected = Files.readAllLines(FileSystems.getDefault().getPath("test-data", "output.txt"));

    String systemOutContents = systemOutStream.toString();

    int i = 0;
    try(Scanner scanner = new Scanner(systemOutContents)) {
      while(scanner.hasNextLine()) {
        String line = scanner.nextLine();
        assertEquals("Mismatched output on line " + (i + 1), expected.get(i), line);
        i++;
      }
    }

    System.setOut(null);
  }
}
