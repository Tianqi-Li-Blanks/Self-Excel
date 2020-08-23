package edu.cs3500.spreadsheets.provider.view;

import java.util.HashMap;
import java.util.Map;

import edu.cs3500.spreadsheets.model.EvaluateVisitor;
import edu.cs3500.spreadsheets.model.ModelSpreadsheet;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.ICellContent;
import edu.cs3500.spreadsheets.model.IValue;
import edu.cs3500.spreadsheets.provider.Formula;
import edu.cs3500.spreadsheets.provider.ICell;
import edu.cs3500.spreadsheets.provider.Value;

/**
 * create a adapter model class for writing his model by our model class.
 */
public class AdapterModel implements edu.cs3500.spreadsheets.provider.ModelSpreadsheet {

  ModelSpreadsheet model;

  /**
   * will input a ModelSpreadsheet which is our model.
   *
   * @param model a model class for our code
   */
  public AdapterModel(ModelSpreadsheet model) {
    this.model = model;
  }

  @Override
  public ICell getCellAt(Coord coord) {
    return new ICell(coord, this.model);
  }

  @Override
  public ICell createCellModel(Coord coord, Formula f) {
    return null;
  }

  @Override
  public Value evaluate(Coord coord, edu.cs3500.spreadsheets.provider.ModelSpreadsheet model) {
    IValue iv = this.model.getWorksheet().get(coord)
            .accept(new EvaluateVisitor(this.model.getWorksheet()));
    return new Value(iv);
  }

  @Override
  public void delete(Coord coord) {
    this.model.removeCell(coord);
  }

  @Override
  public void modify(Coord coord, String contents) {
    this.model.addAndUpdate(coord.col, coord.row, contents);
  }

  @Override
  public boolean visitAll() {
    return false;
  }

  @Override
  public HashMap<Coord, ICell> getGrid() {
    HashMap<Coord, ICell> result = new HashMap<>();
    for (Map.Entry<Coord, ICellContent> entry : this.model.getWorksheet().entrySet()) {
      Coord c = entry.getKey();
      ICell ic = new ICell(c, this.model);
      result.put(c, ic);
    }
    return result;
  }
}
