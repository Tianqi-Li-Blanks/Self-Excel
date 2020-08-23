import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import edu.cs3500.spreadsheets.model.Function;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.ValueBoolean;
import edu.cs3500.spreadsheets.model.ValueDouble;
import edu.cs3500.spreadsheets.model.ValueString;
import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SString;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Visitor;

import static org.junit.Assert.assertEquals;

/**
 * Create a test for all method of {@link Visitor}s.
 */
public class VisitorTest {

  SNumber sNumber;
  SSymbol sSymbol;
  SBoolean sBoolean;
  SList sList2;
  SString sString;
  SList sList1;


  @Before
  public void initial() {
    sSymbol = new SSymbol("A1:B2");
    sBoolean = new SBoolean(true);
    sList1 = new SList();
    sString = new SString("F");
    sNumber = new SNumber(5.0);
    sList2 = new SList(new ArrayList<>(Arrays.asList(
            new SSymbol("SUM"), new SNumber(3), new SNumber(2))));
  }


  @Test
  public void testVisitSymbol() {
    this.initial();
    assertEquals(new Reference("A1:B2").hashCode(),
            sSymbol.accept(new Visitor()).hashCode());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testVisitSymbolFalse() {
    this.initial();
    assertEquals(new Reference("AB22:3B").hashCode(),
            sSymbol.accept(new Visitor()).hashCode());
  }

  @Test
  public void testVisitBoolean() {
    this.initial();
    assertEquals(new ValueBoolean(true).hashCode(),
            sBoolean.accept(new Visitor()).hashCode());
  }

  @Test
  public void testVisitNumber() {
    this.initial();
    assertEquals(new ValueDouble(5.0).hashCode(),
            sNumber.accept(new Visitor()).hashCode());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testVisitSList1() {
    this.initial();
    sList1.accept(new Visitor());
  }

  @Test
  public void testVisitSList2() {
    this.initial();
    ArrayList arr = new ArrayList();
    arr.add(new ValueDouble(3.0));
    arr.add(new ValueDouble(2.0));
    assertEquals(new Function("SUM", arr), sList2.accept(new Visitor()));
  }


  @Test
  public void visitString() {
    this.initial();
    assertEquals(new ValueString("F").hashCode(),
            sString.accept(new Visitor()).hashCode());
  }


}
