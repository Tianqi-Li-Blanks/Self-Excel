package edu.cs3500.spreadsheets.provider.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;


/**
 * View class that creates the visualization of the excel Spreadsheet.
 */
public class VisualSpreadsheetView extends JFrame implements View {


  private AdapterModel model;

  /**
   * constructs the the JFrame .
   */
  public VisualSpreadsheetView(AdapterModel model) {
    super();
    this.model = model;

  }

  /**
   * Renders the model into the Appendable file.
   *
   * @throws IOException if the rendering fails for some reason .
   */
  @Override
  public void render() throws IOException {
    Grid grid;

    JScrollPane scroller;
    this.setTitle("Excel Spreadsheet!");

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    grid = new Grid(model);
    this.setSize(grid.getPreferredSize().width, grid.getPreferredSize().height);

    scroller = new JScrollPane(grid, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scroller.setPreferredSize(new Dimension(20, 0));
    this.setVisible(true);
    this.add(scroller);

  }


}
