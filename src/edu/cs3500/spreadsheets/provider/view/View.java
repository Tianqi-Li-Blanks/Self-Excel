package edu.cs3500.spreadsheets.provider.view;

import java.io.IOException;

/**
 * interface that represents the viewing of this program.
 */
public interface View {

  /**
   * Renders the model into the Appendable file.
   *
   * @throws IOException if the rendering fails for some reason .
   */
  void render() throws IOException;

}
