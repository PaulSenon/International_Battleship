/**
 * author: MadProgrammer
 * from stack overflow post : https://stackoverflow.com/questions/14635952/java-layout-proportions-creating-a-scalable-square-panel
 * license : Creative Commons Attribution-Share Alike
 * 
 * description : It a Layout manager that display a grid 
 * that can be resized keeping the 1:1 ration of each cell
 * 
 * => we made few minor changes
 */

package View;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomGridLayoutManager implements LayoutManager2 {

    private Map<Point, Component> mapComps;

    public CustomGridLayoutManager() {
        mapComps = new HashMap<>();
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        if (constraints instanceof Point) {

            mapComps.put((Point) constraints, comp);

        } else {

            throw new IllegalArgumentException("ChessBoard constraints must be a Point");

        }
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return preferredLayoutSize(target);
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0f;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0f;
    }

    @Override
    public void invalidateLayout(Container target) {
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        Point[] keys = mapComps.keySet().toArray(new Point[mapComps.size()]);
        for (Point p : keys) {
            if (mapComps.get(p).equals(comp)) {
                mapComps.remove(p);
                break;
            }
        }
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return new CellGrid(mapComps).getPreferredSize();
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return preferredLayoutSize(parent);
    }

    @Override
    public void layoutContainer(Container parent) {
        int width = parent.getWidth();
        int height = parent.getHeight();

        int gridSize = Math.min(width, height);

        CellGrid grid = new CellGrid(mapComps);
        int rowCount = grid.getRowCount();
        int columnCount = grid.getColumnCount();

        int cellSize = gridSize / Math.max(rowCount, columnCount);

        int xOffset = (width - (cellSize * columnCount)) /2;
        int yOffset = (height - (cellSize * rowCount)) /2;

        Map<Integer, List<CellGrid.Cell>> cellRows = grid.getCellRows();
        for (Integer row : cellRows.keySet()) {
            List<CellGrid.Cell> rows = cellRows.get(row);
            for (CellGrid.Cell cell : rows) {
                Point p = cell.getPoint();
                Component comp = cell.getComponent();

                int x = xOffset + (p.x * cellSize);
                int y = yOffset + (p.y * cellSize);

                comp.setLocation(x, y);
                comp.setSize(cellSize, cellSize);

            }
        }

    }

    public class CellGrid {

        private Dimension prefSize;
        private int cellWidth;
        private int cellHeight;

        private Map<Integer, List<Cell>> mapRows;
        private Map<Integer, List<Cell>> mapCols;

        public CellGrid(Map<Point, Component> mapComps) {
            mapRows = new HashMap<>();
            mapCols = new HashMap<>();
            for (Point p : mapComps.keySet()) {
                int row = p.y;
                int col = p.x;
                List<Cell> rows = mapRows.get(row);
                List<Cell> cols = mapCols.get(col);
                if (rows == null) {
                    rows = new ArrayList<>();
                    mapRows.put(row, rows);
                }
                if (cols == null) {
                    cols = new ArrayList<>();
                    mapCols.put(col, cols);
                }
                Cell cell = new Cell(p, mapComps.get(p));
                rows.add(cell);
                cols.add(cell);
            }

            int rowCount = mapRows.size();
            int colCount = mapCols.size();

            cellWidth = 0;
            cellHeight = 0;

            for (List<Cell> comps : mapRows.values()) {
                for (Cell cell : comps) {
                    Component comp = cell.getComponent();
                    cellWidth = Math.max(cellWidth, comp.getPreferredSize().width);
                    cellHeight = Math.max(cellHeight, comp.getPreferredSize().height);
                }
            }

            int cellSize = Math.max(cellHeight, cellWidth);

            prefSize = new Dimension(cellSize * colCount, cellSize * rowCount);
        }

        public int getRowCount() {
            return getCellRows().size();
        }

        public int getColumnCount() {
            return getCellColumns().size();
        }

        public Map<Integer, List<Cell>> getCellColumns() {
            return mapCols;
        }

        public Map<Integer, List<Cell>> getCellRows() {
            return mapRows;
        }

        public Dimension getPreferredSize() {
            return prefSize;
        }

        public int getCellHeight() {
            return cellHeight;
        }

        public int getCellWidth() {
            return cellWidth;
        }

        public class Cell {

            private Point point;
            private Component component;

            public Cell(Point p, Component comp) {
                this.point = p;
                this.component = comp;
            }

            public Point getPoint() {
                return point;
            }

            public Component getComponent() {
                return component;
            }

        }

    }

    
}
