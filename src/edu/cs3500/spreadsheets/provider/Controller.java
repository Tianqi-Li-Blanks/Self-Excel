package edu.cs3500.spreadsheets.provider;

import java.io.IOException;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.view.AdapterModel;
import edu.cs3500.spreadsheets.provider.view.EditorView;
import edu.cs3500.spreadsheets.provider.view.View;

/**
 * this is a missing class in the code they give us.
 * create a controller which implement Features and IControllerProvider
 * use to change the cell.
 */
public class Controller implements Features, IControllerProvider {

  private ModelSpreadsheet model;
  private View view;

  /**
   * A controller contain a ModelSpreadsheet and a editor view.
   *
   * @param model a ModelSpreadsheet which we will use adapter model
   * @param view a view which should be a editView
   */
  public Controller(ModelSpreadsheet model, View view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void modifyCell(Coord c, String str) {
    this.model.modify(c, str);
  }

  @Override
  public void start() throws IOException {
    EditorView ed = new EditorView((AdapterModel) model);
    ed.render();
    ed.setVisible(true);
  }
}
