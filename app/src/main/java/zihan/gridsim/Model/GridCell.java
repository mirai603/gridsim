package zihan.gridsim.Model;

import zihan.gridsim.R;

/**
 * This is the base class for anything being displayed on the simulation grid
 * Created by mdp on 1/23/2017.
 */

public class GridCell {
    protected int resourceID; /// The resource ID for the image to display
    protected int rawValue; /// The value as represented on the server
    protected int row, col; /// The location of this cell on the grid

    public GridCell(int val, int r, int c) {
        rawValue = val;
        row = r;
        col = c;
        resourceID = R.drawable.blank;
    }
    public Integer getResourceID() { return resourceID; }
    public int getOrientation() { return 0; }
    public int getRotation() { return 0; }
    public int getRawValue() { return rawValue; }



    public String getCellType() { return "Empty"; }
    public String getCellInfo() { return "Location: (" + col + ", " + row + ")"; }
}
