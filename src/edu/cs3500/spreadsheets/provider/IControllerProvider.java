package edu.cs3500.spreadsheets.provider;

import java.io.IOException;

/**
 * Allows the user to create a certain type of spreadsheet.
 */
public interface IControllerProvider {

  /**
   * This method creates the type of spreadsheet that the user chose in the command line.
   *
   * @throws IOException from the render method.
   */
  void start() throws IOException;
}
