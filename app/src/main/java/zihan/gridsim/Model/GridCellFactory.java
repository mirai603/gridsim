package zihan.gridsim.Model;

import android.util.Log;

/**
 * Created by mdp on 1/23/2017.
 * Gutted by mdp on 1/5/2024 as starting point for new Milestone 2
 */

public class GridCellFactory {
    public GridCell makeCell(int val, int row, int col) {

        GridCell tempCell = null;

        if (val == 0) {
            tempCell = new GridCell(val, row, col);
        } else if (val >= 1000 && val <= 3000) {
            tempCell = new Plant(val, row, col);
        } else if (val > 4000 && val < 1000000) {
            // reserved
        } else if (val >= 1000000 && val < 2000000) {
            tempCell = new TurnableGardenerItem(val, row, col);

        } else if (val >= 2000000 && val < 3000000) {
            tempCell = new GardenerItem(val, row, col);
        } else if (val >= 10000000 && val < 20000000) {
            tempCell = new TurnableGardenerItem(val, row, col);
        } else {
            // ??
            //Log.d("grid ID error", Integer.toString(val));
            tempCell = new GridCell(0, row, col);
        }

        return tempCell;
    }

}
