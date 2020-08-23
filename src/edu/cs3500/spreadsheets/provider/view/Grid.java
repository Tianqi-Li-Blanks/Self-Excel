package edu.cs3500.spreadsheets.provider.view;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.ICell;

/**
 * Represents the JPanel for the excelspreadsheet. Each cell is represented by a rectangle within
 * this Grid JPanel that is painted on in the painComponent method.
 */
public class Grid extends JPanel {

  AdapterModel model;
  private final int WITDH = 100;
  private final int HEIGHT = 50;
  Coord selected;
  boolean isSelected;

  /**
   * constructs the grid.
   */
  public Grid(AdapterModel model) {


    this.model = model;
    this.setVisible(true);
    this.setPreferredSize(new Dimension(WITDH * 10, HEIGHT * 10));

  }


  /**
   * overides Jpanel's getPreferredSize method. gets the prefered size of the whole grid.
   */
  @Override
  public Dimension getMinimumSize() {

    return new Dimension(1000,
            500);

  }

  /**
   * This method updates the isSelected field which represents if a cell (the select parameter) in
   * the JPanel has been selected. updates the isSelected field and the selected field which
   * represents which cell has been selected.
   */
  protected void updateSelectedCells(Coord select) {

    selected = select;
    isSelected = true;

  }

  /**
   * overides the JPanel method. paints the whole grid / Panel.
   *
   * @param g .
   */
  @Override
  public void paintComponent(Graphics g) {

    Graphics2D g2 = (Graphics2D) g;

    int totalrow = getHeight() / HEIGHT;

    int totalCol = getWidth() / WITDH;

    float thickness = 3;

    Stroke oldStroke = g2.getStroke();

    for (int col = 0; col <= totalCol; col++) {

      for (int row = 0; row <= totalrow; row++) {

        g2.drawRect(col * WITDH, row * HEIGHT, WITDH, HEIGHT);

        if (col == 0 && row != 0 && row <= totalrow) {

          g2.drawString(Integer.toString(row), (col * WITDH) + 50, ((row + 1) * HEIGHT));

        } else if (row == 0 && col != 0 && col <= totalCol) {

          g2.drawString(Coord.colIndexToName(col), (col * WITDH) + 50, ((row + 1) * HEIGHT));

        }

        if (row != 0 && col != 0 && this.model.getCellAt(new Coord(col, row)) != null) {

          ICell cell = this.model.getCellAt(new Coord(col, row));
          try {
            String str = " " + cell.getFormula().evaluate().toString();
            if (str.length() > 13) {
              str = str.substring(0, 13);
            }
            g2.drawString(str, (col * WITDH), ((row + 1) * HEIGHT));
          } catch (IllegalArgumentException e) {
            g2.drawString("there is a cycle", (col * WITDH), ((row + 1) * HEIGHT));
          }
          if (isSelected) {
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRect(selected.col * WITDH, selected.row * HEIGHT, WITDH, HEIGHT);
            g2.setStroke(oldStroke);
          }

        }
      }
    }
  }
}
