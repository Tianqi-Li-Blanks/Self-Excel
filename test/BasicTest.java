import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.FormulaValue;
import edu.cs3500.spreadsheets.model.Function;
import edu.cs3500.spreadsheets.model.ICellContent;
import edu.cs3500.spreadsheets.model.ModelSpreadsheet;
import edu.cs3500.spreadsheets.model.ProductVisitor;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.SmallVisitor;
import edu.cs3500.spreadsheets.model.SubVisitor;
import edu.cs3500.spreadsheets.model.SumVisitor;
import edu.cs3500.spreadsheets.model.ValueBoolean;
import edu.cs3500.spreadsheets.model.ValueDouble;
import edu.cs3500.spreadsheets.model.ValueString;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * use to test whether Basic worksheet can work well.
 */

public class BasicTest {

  ModelSpreadsheet basic;

  @Test
  public void testEmptySheet() {
    basic = new ModelSpreadsheet();
    assertEquals(0, basic.getWorksheet().size());
  }

  @Test
  public void testAddElementToSheet() {
    basic = new ModelSpreadsheet();
    Coord c = new Coord(5, 5);
    String in = "\"example\"";
    basic.addAndUpdate(5, 5, in);
    assertEquals("\"example\"", basic.getContent(5, 5, false));
  }

  @Test(expected = IllegalArgumentException.class)
  public void selfReferentialFormula() {
    basic = new ModelSpreadsheet();
    String in = "=(SUM F5 F6)";
    basic.addAndUpdate(5, 5, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void IndirectSelfReferentialFormula() {
    basic = new ModelSpreadsheet();
    String in = "=(SUM (PRODUCT F5 A5) F6)";
    basic.addAndUpdate(5, 5, in);
  }

  @Test
  public void testBlankCell() {
    basic = new ModelSpreadsheet();
    Coord c = new Coord(5, 5);
    assertNull(basic.getWorksheet().get(c));
  }

  @Test
  public void testNumericValue() {
    basic = new ModelSpreadsheet();
    Coord c = new Coord(5, 5);
    String in = "6.0";
    basic.addAndUpdate(5, 5, in);
    assertEquals(new ValueDouble(6.0), basic.getWorksheet().get(c));
  }

  @Test
  public void testBooleanValue() {
    basic = new ModelSpreadsheet();
    Coord c = new Coord(5, 5);
    String in = "false";
    basic.addAndUpdate(5, 5, in);
    assertEquals(new ValueBoolean(false), basic.getWorksheet().get(c));
  }

  @Test
  public void testStringValue() {
    basic = new ModelSpreadsheet();
    Coord c = new Coord(5, 5);
    String in = "ab";
    basic.addAndUpdate(5, 5, in);
    assertEquals(new ValueString("ab"), basic.getWorksheet().get(c));
  }

  @Test
  public void testFormulasFunction() {
    basic = new ModelSpreadsheet();
    Coord c = new Coord(5, 5);
    String in = "E5 =(SUM 1 2)";
    basic.addAndUpdate(5, 5, in);
    List<ICellContent> list = new ArrayList<>();
    list.add(new ValueDouble(1.0));
    list.add(new ValueDouble(2.0));
    assertEquals(new Function("SUM", list), basic.getWorksheet().get(c));
  }

  @Test
  public void testFormulasValueDouble() {
    basic = new ModelSpreadsheet();
    Coord c = new Coord(5, 5);
    String in = "=1";
    basic.addAndUpdate(5, 5, in);
    assertTrue(new FormulaValue(new ValueDouble(1.0)).equals(basic.getWorksheet().get(c)));
  }

  @Test
  public void testFormulasValueString() {
    basic = new ModelSpreadsheet();
    Coord c = new Coord(5, 5);
    String in = "=AB";
    basic.addAndUpdate(5, 5, in);
    assertTrue(new FormulaValue(new ValueString("AB")).equals(basic.getWorksheet().get(c)));
  }


  @Test
  public void testFormulasValueBoolean() {
    basic = new ModelSpreadsheet();
    Coord c = new Coord(5, 5);
    String in = "=true";
    basic.addAndUpdate(5, 5, in);
    assertTrue(new FormulaValue(new ValueBoolean(true)).equals(basic.getWorksheet().get(c)));
  }

  @Test
  public void testFormulasReference() {
    basic = new ModelSpreadsheet();
    Coord c = new Coord(5, 5);
    String in = "=A1:B5";
    basic.addAndUpdate(5, 5, in);
    assertEquals(new Reference("A1:B5"), basic.getWorksheet().get(c));
  }

  @Test
  public void testSUM() {
    basic = new ModelSpreadsheet();
    List<ICellContent> list = new ArrayList<>();
    list.add(new ValueDouble(1.0));
    list.add(new ValueDouble(2.0));
    assertTrue(Math.abs(3.0 - new Function("SUM", list)
            .accept(new SumVisitor(basic.getWorksheet())).getDouble()) <= 0.00001);
  }

  @Test
  public void testPRODUCT() {
    basic = new ModelSpreadsheet();
    List<ICellContent> list = new ArrayList<>();
    list.add(new ValueDouble(1.0));
    list.add(new ValueDouble(2.0));
    assertTrue(Math.abs(2.0 - new Function("PRODUCT", list)
            .accept(new ProductVisitor(basic.getWorksheet())).getDouble()) <= 0.00001);

    basic.addAndUpdate(1, 2, "(SUM 1 2)");
    assertEquals("2.0", basic.getContent(1, 2, false));
  }

  @Test
  public void testSmall() {
    basic = new ModelSpreadsheet();
    List<ICellContent> list = new ArrayList<>();
    list.add(new ValueDouble(1.0));
    list.add(new ValueDouble(2.0));
    assertTrue(new Function("<", list)
            .accept(new SmallVisitor(basic.getWorksheet())).getBoolean());

    basic.addAndUpdate(1, 2, "(< 1 2)");
    assertEquals("true", basic.getContent(1, 2, false));
  }

  @Test
  public void testSUB() {
    basic = new ModelSpreadsheet();
    List<ICellContent> list = new ArrayList<>();
    list.add(new ValueDouble(1.0));
    list.add(new ValueDouble(2.0));
    assertTrue(Math.abs(-1.0 - new Function("SUB", list)
            .accept(new SubVisitor(basic.getWorksheet())).getDouble()) <= 0.00001);

    basic.addAndUpdate(1, 2, "(SUB 1 2)");
    assertEquals("-1.0", basic.getContent(1, 2, false));
  }

  @Test
  public void testSumReferSameCell() {
    basic = new ModelSpreadsheet();
    List<ICellContent> list = new ArrayList<>();
    Coord a = new Coord(1, 1);
    basic.addAndUpdate(1, 2, "5.0");
    list.add((ICellContent) basic.getWorksheet().get(a));
    list.add((ICellContent) basic.getWorksheet().get(a));
    assertTrue(Math.abs(10.0 - new Function("SUM", list)
            .accept(new SumVisitor(basic.getWorksheet())).getDouble()) <= 0.00001);

    basic.addAndUpdate(2, 2, "(SUB A1 A1)");
    assertEquals("10.0", basic.getContent(2, 2, false));
  }

  @Test
  public void testSumReference() {
    basic = new ModelSpreadsheet();
    List<ICellContent> list = new ArrayList<>();
    Coord a = new Coord(1, 1);
    Coord b = new Coord(2, 1);
    basic.addAndUpdate(1, 1, "5.0");
    basic.addAndUpdate(1, 2, "2.0");
    list.add((ICellContent) basic.getWorksheet().get(a));
    list.add((ICellContent) basic.getWorksheet().get(b));
    assertTrue(Math.abs(7.0 - new Function("SUM", list)
            .accept(new SumVisitor(basic.getWorksheet())).getDouble()) <= 0.00001);

    basic.addAndUpdate(3, 2, "(SUB A1:A2)");
    assertEquals("7.0", basic.getContent(3, 2, false));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSumIncorrectArgument() {
    basic = new ModelSpreadsheet();
    List<ICellContent> list = new ArrayList<>();
    Coord a = new Coord(1, 1);
    Coord b = new Coord(2, 1);
    basic.addAndUpdate(1, 1, "5.0");
    basic.addAndUpdate(2, 1, "Invalid");
    list.add((ICellContent) basic.getWorksheet().get(a));
    list.add((ICellContent) basic.getWorksheet().get(b));
    new Function("SUM", list).accept(new SumVisitor(basic.getWorksheet()));
  }

  @Test
  public void testTenderingProperly() {
    basic = new ModelSpreadsheet();
    basic.addAndUpdate(1, 2, "(SUB 1 2)");
    assertEquals("3.0", basic.getContent(1, 2, false));
  }
}