package zihan.gridsim.Model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import zihan.gridsim.R;

// WHERE IS WHEEL FADE? <<<<< !!!!!
// import static zihan.gridsim.R.drawable.wheel_fade;

/**
 * Created by mdp on 1/23/2017.
 */

public class SimulationGrid {
    private int numRows, numCols;
    private GridCell[] cells;
    private GridCell badCell = new GridCell(0, -1, -1);

    public SimulationGrid(int rows, int cols) {
        numRows = rows;
        numCols = cols;
        cells = new GridCell[numRows * numCols];
    }

    public GridCell getCell(int index) {
        if (index < 0 || index >= numRows * numCols || cells[index] == null)
            return badCell;
        return cells[index];
    }

    public GridCell getCell(int row, int col) {
        return getCell(row * numCols + col);
    }

    public int getNumRows() { return numRows; }
    public int getNumCols() { return numCols; }

    public void setCell(int index, GridCell cell) {
        if (index >= 0 && index < numRows * numCols)
            cells[index] = cell;
    }

    public void setCell(int row, int col, GridCell cell) {
        setCell(row * numCols + col, cell);
    }

    public int size() {return numRows * numCols;}

    public void setUsingJSON(JSONArray arr) throws JSONException {
        //TODO: fill in your implementation

        GridCellFactory factory = new GridCellFactory();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int ID = Integer.parseInt(arr.getJSONArray(i).get(j).toString());
                setCell(i, j, factory.makeCell(ID, i, j));
            }
        }

    }
}
