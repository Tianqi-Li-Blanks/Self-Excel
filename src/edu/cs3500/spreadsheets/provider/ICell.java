package edu.cs3500.spreadsheets.provider;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * this is missing class for the code them give us,
 * create ICell which has a coord and our own model for let us can directly use our model's method.
 */
public class ICell {
  private Coord coord;
  private edu.cs3500.spreadsheets.model.ModelSpreadsheet model;

  /**
   * create ICell which input a coord and our own model
   * for let us can directly use our model's method.
   *
   * @param coord a coord for coord need show
   * @param model the model which is our data model
   */
  public ICell(Coord coord, edu.cs3500.spreadsheets.model.ModelSpreadsheet model) {
    this.coord = coord;
    this.model = model;
  }

  /**
   * change the class to a Formula type.
   *
   * @return a new Formula which has a true boolean
   */
  public Formula getFormula() {
    return new Formula(coord, model, true);
  }

}
