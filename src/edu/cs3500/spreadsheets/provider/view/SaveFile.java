package edu.cs3500.spreadsheets.provider.view;

import java.io.IOException;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.ICell;

/**
 * View class that saves the model into a given Appendable file. An instance of the View interface.
 * Allows the user to save their spreadsheet to a given Appendable text file.
 */
public class SaveFile implements View {
  private AdapterModel model;
  private Appendable appended;

  /**
   * constrcuts the savefile class .
   */
  public SaveFile(AdapterModel model, Appendable appended) {
    this.model = model;
    this.appended = appended;
  }

  /**
   * Renders the model into the Appendable file.
   *
   * @throws IOException if the rendering fails for some reason .
   */
  @Override
  public void render() throws IOException {

    for (Coord c : this.model.getGrid().keySet()) {
      ICell cell = this.model.getGrid().get(c);
      String strFormula = " " + cell.getFormula().toString();
      appended.append(c.toString() + " " + strFormula + '\n');

    }
  }
}
