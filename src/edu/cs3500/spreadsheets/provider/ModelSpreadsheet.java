package edu.cs3500.spreadsheets.provider;

import java.util.HashMap;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * Represents our model Interface.
 */
public interface ModelSpreadsheet {

  /**
   * get the cell at a specific coord.
   *
   * @param coord coord of the cell to be gotten
   * @return the cell at the given coord.
   */
  // ICell getCellAt(Coord coord);
  // we change it for our class for just can run the program,
  // because we do not have their ICell class and Formula class,
  // and we will not use this method,so it will be just ignore.

  ICell getCellAt(Coord coord);

  /**
   * Creates a cell given its coordinates and formula.
   *
   * @param coord the coord of the cell to be created
   * @param f     the formula of the new cell.
   */
  // ICell createCellModel(Coord coord, Formula f);
  // we change ICell and Formula to for our class for just can run the program,
  // because we do not have their ICell class and Formula class,
  // and we will not use this method, so it will be just ignore.

  ICell createCellModel(Coord coord, Formula f);

  /**
   * Evaluates the cell at a coordinate.
   *
   * @param coord the coord of the cell to be evaluated.
   * @param model the spreadsheet the cell is going to be evaluated on.
   * @return Value  the evaluated cell.
   */
  //Value evaluate(Coord coord, ModelSpreadsheet model);
  // we change Value to for our class for just can run the program,
  // because we do not have their Value class and Formula class,
  // and we will not use this method, so it will be just ignore.

  Value evaluate(Coord coord, ModelSpreadsheet model);

  /**
   * deletes the cell at the given coord.
   *
   * @param coord coord of the cell to be deleted.
   */
  void delete(Coord coord);

  /**
   * modify the content of the given cell with the given contents.
   *
   * @param coord    coord of cell to be modified.
   * @param contents contents of cell to be changed.
   */
  void modify(Coord coord, String contents);

  /**
   * Visits all the grids entries.
   *
   * @return true if all the entries have been visited.
   */
  boolean visitAll();

  HashMap<Coord, ICell> getGrid();
}