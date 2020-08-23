package edu.cs3500.spreadsheets.provider.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.Controller;
import edu.cs3500.spreadsheets.provider.Features;
import edu.cs3500.spreadsheets.provider.Formula;
import edu.cs3500.spreadsheets.provider.ModelSpreadsheet;

/**
 * Represents another type of the view. This view allows for infinite scrolling and allows users to
 * edit a cell by inputing a Formula into the provided JTextField.
 */
public class EditorView extends JFrame implements View, MouseListener {
  JScrollPane scroller;
  private AdapterModel model;
  private Features feature;
  Grid grid;
  private JTextField formulaPanel;
  JButton right;
  JButton incorrect;
  private Coord cellClicked;

  /**
   * creates an instance of the editable view which enables the user to modify the spreadsheet .
   */
  public EditorView(AdapterModel model) throws HeadlessException {
    this.model = model;
    feature = new Controller((ModelSpreadsheet) model, this);

    this.setTitle("Excel Spreadsheet!");

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    right = new JButton("correct");
    incorrect = new JButton("incorrect");
    formulaPanel = new JTextField();

    JPanel overlay = new JPanel();
    JPanel buttonText = new JPanel();
    buttonText.setLayout(new GridLayout(1, 3));
    buttonText.add(incorrect);
    buttonText.add(right);
    buttonText.add(formulaPanel);
    overlay.setLayout(new BorderLayout());
    grid = new Grid(model);
    overlay.setPreferredSize(new Dimension(grid.getPreferredSize().width,
            grid.getPreferredSize().height + 20));
    overlay.setSize(new Dimension(grid.getPreferredSize().width,
            grid.getPreferredSize().height + 20));
    this.setSize(new Dimension(grid.getPreferredSize().width,
            overlay.getPreferredSize().height + 20));

    scroller = new JScrollPane(grid, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    overlay.add(buttonText, BorderLayout.NORTH);
    overlay.add(scroller, BorderLayout.CENTER);

    this.getContentPane().add(overlay);

  }

  /**
   * Renders the edittable grid model into the Appendable file. Includes all the buttons and grid
   * and infinite scrolling.
   *
   * @throws IOException if the rendering fails for some reason .
   */
  @Override
  public void render() throws IOException {
    grid.addMouseListener(this);
    scroller.getVerticalScrollBar().addAdjustmentListener(e -> {

      int visible = scroller.getVerticalScrollBar().getVisibleAmount();
      int val = scroller.getVerticalScrollBar().getValue();
      int max = scroller.getVerticalScrollBar().getMaximum();

      if (val == (max - visible)) {
        grid.setPreferredSize(new Dimension(grid.getPreferredSize().width,
                grid.getPreferredSize().height + 500));
      }

    });

    scroller.getHorizontalScrollBar().addAdjustmentListener(e -> {
      int visible = scroller.getHorizontalScrollBar().getVisibleAmount();
      int val = scroller.getHorizontalScrollBar().getValue();
      int max = scroller.getHorizontalScrollBar().getMaximum();

      if (val == (max - visible)) {
        grid.setPreferredSize(new Dimension(grid.getPreferredSize().width + 1000,
                grid.getPreferredSize().height));
      }
    });

    this.actionListener();

  }


  /**
   * Overrides the mouseClicked method in the JFrame class. computes the selected cell based on
   * where the user selected on the JPanel graph.
   */
  @Override
  public void mouseClicked(MouseEvent e) {

    int col = Math.round(e.getX() / 100);
    int row = Math.round(e.getY() / 50);

    if (row > 0 && col > 0) {
      Coord coord = new Coord(col, row);
      this.cellClicked = coord;

      grid.updateSelectedCells(coord);
      if (model.getCellAt(coord) != null) {
        Formula formula = model.getCellAt(coord).getFormula();
        formulaPanel.setText(formula.toString());
      }
      this.repaint();
    }

  }


  private void actionListener() {
    this.right.addActionListener(e1 -> {
      String formula = formulaPanel.getText();
      this.feature.modifyCell(this.cellClicked, formula);
      this.repaint();
    });

    this.incorrect.addActionListener(e12 -> {
      this.formulaPanel.setText("");
    });
  }

  @Override
  public void mousePressed(MouseEvent e) {
    return;
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    return;
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    return;
  }

  @Override
  public void mouseExited(MouseEvent e) {
    return;
  }
}
