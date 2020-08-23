package edu.cs3500.spreadsheets.provider;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * represents the feature of being able to edit a given cell.
 */
public interface Features {

  /**
   * Modifies the given cell with the given str formula. this method will only be called by the
   * Editorview class.
   *
   * @param c   the coord of the cell to edit/modify.
   * @param str the new formula of the cell that should be modified.
   */
  void modifyCell(Coord c, String str);

}
