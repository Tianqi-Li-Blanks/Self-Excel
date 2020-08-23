import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import edu.cs3500.spreadsheets.model.ModelSpreadsheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.BasicTextualView;
import edu.cs3500.spreadsheets.view.ISpreadsheetView;

import static org.junit.Assert.assertTrue;

/**
 * Create a test for all method of {@link BasicTextualView}s.
 */
public class BasicTextualViewTest {

  @Test
  public void render() {
    try {
      FileReader fileReader = new FileReader("test/abs.txt");
      ModelSpreadsheet worksheet = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader);
      PrintWriter p = new PrintWriter("test/absOutput.txt");
      ISpreadsheetView view = new BasicTextualView(worksheet, p);
      view.render();
      p.close();

      FileReader fileReader1 = new FileReader("test/abs.txt");
      FileReader fileReader2 = new FileReader("test/absOutput.txt");
      ModelSpreadsheet model1 = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader1);
      ModelSpreadsheet model2 = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader2);

      assertTrue(model1.sameModel(model2));

    } catch (Exception e) {
      System.out.println("wrong");
    }
  }

  @Test
  public void testTextualViewOfEmptySpreadsheet() {
    try {
      FileReader fileReader = new FileReader("test/emptySpreadsheet.txt");
      ModelSpreadsheet worksheet = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader);
      PrintWriter p = new PrintWriter("test/emptySpreadsheetOutput.txt");
      ISpreadsheetView view = new BasicTextualView(worksheet, p);
      view.render();
      p.close();

      FileReader fileReader1 = new FileReader("test/emptySpreadsheet.txt");
      FileReader fileReader2 = new FileReader("test/emptySpreadsheetOutput.txt");
      ModelSpreadsheet model1 = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader1);
      ModelSpreadsheet model2 = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader2);

      assertTrue(model1.sameModel(model2));

    } catch (Exception e) {
      System.out.println("wrong");
    }
  }

  @Test
  public void testTextualViewOfReference() {
    try {
      FileWriter fw = new FileWriter("test/referenceSpreadsheet.txt");
      PrintWriter pw = new PrintWriter(fw);
      pw.print("B1 6\n");
      pw.print("B2 7\n");
      pw.print("A1 =B1:B2\n");
      pw.close();
      FileReader fileReader = new FileReader("test/referenceSpreadsheet.txt");
      ModelSpreadsheet worksheet = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader);
      PrintWriter p = new PrintWriter("test/referenceSpreadsheetOutput.txt");
      ISpreadsheetView view = new BasicTextualView(worksheet, p);
      view.render();
      p.close();

      FileReader fileReader1 = new FileReader("test/referenceSpreadsheet.txt");
      FileReader fileReader2 = new FileReader("test/referenceSpreadsheetOutput.txt");
      ModelSpreadsheet model1 = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader1);
      ModelSpreadsheet model2 = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader2);

      assertTrue(model1.sameModel(model2));

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  public void testTextualViewOfFunction() {
    try {
      FileWriter fw = new FileWriter("test/functionSpreadsheet.txt");
      PrintWriter pw = new PrintWriter(fw);
      pw.print("B1 6\n");
      pw.print("B2 7\n");
      pw.print("A1 =(SUM B1 B2)\n");
      pw.print("A2 =(SUB B1 B2)\n");
      pw.print("A3 =(PRD B1 B2)\n");
      pw.print("A4 =(< B1 B2)\n");
      pw.close();
      FileReader fileReader = new FileReader("test/functionSpreadsheet.txt");
      ModelSpreadsheet worksheet = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader);
      PrintWriter p = new PrintWriter("test/functionSpreadsheetOutput.txt");
      ISpreadsheetView view = new BasicTextualView(worksheet, p);
      view.render();
      p.close();

      FileReader fileReader1 = new FileReader("test/functionSpreadsheet.txt");
      FileReader fileReader2 = new FileReader("test/functionSpreadsheetOutput.txt");
      ModelSpreadsheet model1 = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader1);
      ModelSpreadsheet model2 = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader2);

      assertTrue(model1.sameModel(model2));

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  public void testTextualViewOfIValue() {
    try {
      FileWriter fw = new FileWriter("test/IvalueSpreadsheet.txt");
      PrintWriter pw = new PrintWriter(fw);
      pw.print("B2 7.0\n");
      pw.print("A1 \"a string\"\n");
      pw.print("A2 true\n");
      pw.print("A3 false\n");
      pw.print("A4 =(error)\n");
      pw.close();
      FileReader fileReader = new FileReader("test/IvalueSpreadsheet.txt");
      ModelSpreadsheet worksheet = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader);
      PrintWriter p = new PrintWriter("test/IvalueSpreadsheetOutput.txt");
      ISpreadsheetView view = new BasicTextualView(worksheet, p);
      view.render();
      p.close();

      FileReader fileReader1 = new FileReader("test/IvalueSpreadsheet.txt");
      FileReader fileReader2 = new FileReader("test/IvalueSpreadsheetOutput.txt");
      ModelSpreadsheet model1 = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader1);
      ModelSpreadsheet model2 = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader2);

      assertTrue(model1.sameModel(model2));

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  public void testTextualViewOfError() {
    try {
      FileWriter fw = new FileWriter("test/errorSpreadsheet.txt");
      PrintWriter pw = new PrintWriter(fw);
      pw.print("A1 =(error)\n");
      pw.close();
      FileReader fileReader = new FileReader("test/errorSpreadsheet.txt");
      ModelSpreadsheet worksheet = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader);
      PrintWriter p = new PrintWriter("test/errorSpreadsheetOutput.txt");
      ISpreadsheetView view = new BasicTextualView(worksheet, p);
      view.render();
      p.close();

      FileReader fileReader1 = new FileReader("test/errorSpreadsheet.txt");
      FileReader fileReader2 = new FileReader("test/errorSpreadsheetOutput.txt");
      ModelSpreadsheet model1 = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader1);
      ModelSpreadsheet model2 = WorksheetReader.read(
              new ModelSpreadsheet.BasicWorksheetBuilder(), fileReader2);

      assertTrue(model1.sameModel(model2));

    } catch (Exception e) {
      System.out.println(e);
    }
  }


}