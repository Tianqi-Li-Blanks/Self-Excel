import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.cs3500.spreadsheets.model.ModelSpreadsheet;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.BasicVisualView;
import edu.cs3500.spreadsheets.view.EditableVisualView;

import static org.junit.Assert.assertEquals;

/**
 * a test class for EditableVisualView{@link EditableVisualView}s.
 */
public class EditableVisualViewTest {

  @Test
  public void TestUpdateTextField1() throws FileNotFoundException {
    ModelSpreadsheet worksheet = WorksheetReader.read(new ModelSpreadsheet.BasicWorksheetBuilder(),
            new FileReader("test/functionSpreadsheet.txt"));
    BasicVisualView view = new BasicVisualView(worksheet);
    EditableVisualView ed = new EditableVisualView(view);
    Coord c = new Coord(2, 1);
    ed.updateTextField(c);
    assertEquals("6.0", ed.getTextString());
  }

  @Test
  public void TestUpdateTextField2() throws FileNotFoundException {
    ModelSpreadsheet worksheet = WorksheetReader.read(new ModelSpreadsheet.BasicWorksheetBuilder(),
            new FileReader("test/functionSpreadsheet.txt"));
    BasicVisualView view = new BasicVisualView(worksheet);
    EditableVisualView ed = new EditableVisualView(view);
    Coord c = new Coord(3, 3);
    ed.updateTextField(c);
    assertEquals("", ed.getTextString());
  }

  @Test
  public void TestUpdateTextFiel3() throws FileNotFoundException {
    ModelSpreadsheet worksheet = WorksheetReader.read(new ModelSpreadsheet.BasicWorksheetBuilder(),
            new FileReader("test/functionSpreadsheet.txt"));
    BasicVisualView view = new BasicVisualView(worksheet);
    EditableVisualView ed = new EditableVisualView(view);
    Coord c = new Coord(1, 1);
    ed.updateTextField(c);
    assertEquals("=(SUM B1 B2)", ed.getTextString());
  }

  @Test
  public void getTextString() throws FileNotFoundException {
    ModelSpreadsheet worksheet = WorksheetReader.read(new ModelSpreadsheet.BasicWorksheetBuilder(),
            new FileReader("test/functionSpreadsheet.txt"));
    BasicVisualView view = new BasicVisualView(worksheet);
    EditableVisualView ed = new EditableVisualView(view);
    Coord c = new Coord(1, 1);
    ed.updateTextField(c);
    assertEquals("=(SUM B1 B2)", ed.getTextString());
  }

  @Test
  public void cleanTextString() throws FileNotFoundException {
    ModelSpreadsheet worksheet = WorksheetReader.read(new ModelSpreadsheet.BasicWorksheetBuilder(),
            new FileReader("test/functionSpreadsheet.txt"));
    BasicVisualView view = new BasicVisualView(worksheet);
    EditableVisualView ed = new EditableVisualView(view);
    Coord c = new Coord(1, 1);
    ed.updateTextField(c);
    ed.cleanTextString();
    assertEquals("", ed.getTextString());
  }


  @Test
  public void cleanTextString2() throws FileNotFoundException {
    ModelSpreadsheet worksheet = WorksheetReader.read(new ModelSpreadsheet.BasicWorksheetBuilder(),
            new FileReader("test/functionSpreadsheet.txt"));
    BasicVisualView view = new BasicVisualView(worksheet);
    EditableVisualView ed = new EditableVisualView(view);
    Coord c = new Coord(3, 1);
    ed.updateTextField(c);
    ed.cleanTextString();
    assertEquals("", ed.getTextString());
  }

  @Test
  public void getCoord() throws FileNotFoundException {
    ModelSpreadsheet worksheet = WorksheetReader.read(new ModelSpreadsheet.BasicWorksheetBuilder(),
            new FileReader("test/functionSpreadsheet.txt"));
    BasicVisualView view = new BasicVisualView(worksheet);
    EditableVisualView ed = new EditableVisualView(view);
    assertEquals(null, ed.getCoord());
  }

}