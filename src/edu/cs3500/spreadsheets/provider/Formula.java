package edu.cs3500.spreadsheets.provider;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * create a Formula which has our own model and a coord and a boolean for whether show use to
 * show the row data content (this is missing class for the code them give us).
 *
 */
public class Formula {
  private Coord coord;
  private edu.cs3500.spreadsheets.model.ModelSpreadsheet model;
  private boolean show;

  /**
   * create a Formula which has our own model and a coord and a boolean for whether show use to
   * show the row data content.
   *
   * @param coord a coord for coord need show
   * @param model the model which is our data model
   * @param show boolean
   */
  public Formula(Coord coord, edu.cs3500.spreadsheets.model.ModelSpreadsheet model, boolean show) {
    this.coord = coord;
    this.model = model;
    this.show = show;
  }

  /**
   * the method for change the boolean.
   *
   * @return a new Formula change the boolean is false
   */
  public Formula evaluate() {
    return new Formula(coord, model, false);
  }

  @Override
  public String toString() {
    return this.model.getContent(coord.col, coord.row, show);
  }
}
