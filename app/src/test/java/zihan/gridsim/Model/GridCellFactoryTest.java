package zihan.gridsim.Model;

import junit.framework.TestCase;

import org.junit.Test;

public class GridCellFactoryTest extends TestCase {

    public void testMakeCellCorrectLocation() {
        GridCellFactory factory = new GridCellFactory();
        GridCell test = factory.makeCell(0, 0, 0);
        assertEquals(test.getCellInfo(), "Location: (0, 0)");

        GridCell test2 = factory.makeCell(0, 10, 10);
        assertEquals(test2.getCellInfo(), "Location: (10, 10)");
    }

    public void testMakeCellBlankCorrectData() {
        GridCellFactory factory = new GridCellFactory();
        GridCell test = factory.makeCell(0, 0, 0);
        assertEquals(test.getCellType(), "Empty");
        assertEquals(test.getRawValue(), 0);
    }

    public void testMakeCellPlantCorrectData() {
        GridCellFactory factory = new GridCellFactory();
        GridCell test = factory.makeCell(1000, 0, 0);
        assertEquals(test.getCellType(), "Tree");
        assertEquals(test.getRawValue(), 1000);

        GridCell test2 = factory.makeCell(2002, 0, 0);
        assertEquals(test2.getCellType(), "Clover");
        assertEquals(test2.getRawValue(), 2002);

        GridCell test3 = factory.makeCell(2003, 0, 0);
        assertEquals(test3.getCellType(), "Mushroom");
        assertEquals(test3.getRawValue(), 2003);

        GridCell test4 = factory.makeCell(3000, 0, 0);
        assertEquals(test4.getCellType(), "Sunflower");
        assertEquals(test4.getRawValue(), 3000);

        GridCell test5 = factory.makeCell(1005, 0, 0);
        assertEquals(test5.getCellType(), "Bushes");
        assertEquals(test5.getRawValue(), 1005);
    }

    public void testMakeCellGardenerAllDirection() {
        GridCellFactory factory = new GridCellFactory();
        GridCell up = factory.makeCell(1999000, 0, 0);
        assertEquals(up.getCellType(), "Gardener");
        assertEquals(up.getCellInfo(), "Location: (0, 0)\nGardener ID: 999");
        assertEquals(up.getOrientation(), 0);

        GridCell right = factory.makeCell(1999002, 0, 0);
        assertEquals(right.getOrientation(), 2);

        GridCell down = factory.makeCell(1999004, 0, 0);
        assertEquals(down.getOrientation(), 4);

        GridCell left = factory.makeCell(1999006, 0, 0);
        assertEquals(left.getOrientation(), 6);
    }

    public void testMakeCellShovelAndGardenerID() {
        GridCellFactory factory = new GridCellFactory();
        GridCell test = factory.makeCell(2999000, 0, 0);
        assertEquals(test.getCellType(), "Shovel");
        assertEquals(test.getCellInfo(), "Location: (0, 0)\nGardener ID: 999");
    }

    public void testMakeCellCartAllDataAllDirection() {
        GridCellFactory factory = new GridCellFactory();
        GridCell up = factory.makeCell(19990000, 0, 0);
        assertEquals(up.getCellType(), "Cart");
        assertEquals(up.getCellInfo(), "Location: (0, 0)\nGardener ID: 999");
        assertEquals(up.getOrientation(), 0);

        GridCell right = factory.makeCell(19990002, 0, 0);
        assertEquals(up.getCellType(), "Cart");
        assertEquals(up.getCellInfo(), "Location: (0, 0)\nGardener ID: 999");
        assertEquals(right.getOrientation(), 2);

        GridCell down = factory.makeCell(19990004, 0, 0);
        assertEquals(up.getCellType(), "Cart");
        assertEquals(up.getCellInfo(), "Location: (0, 0)\nGardener ID: 999");
        assertEquals(down.getOrientation(), 4);

        GridCell left = factory.makeCell(19990006, 0, 0);
        assertEquals(up.getCellType(), "Cart");
        assertEquals(up.getCellInfo(), "Location: (0, 0)\nGardener ID: 999");
        assertEquals(left.getOrientation(), 6);

    }
}