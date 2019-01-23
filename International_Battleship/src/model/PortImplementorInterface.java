package model;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import tools.Coord;

public interface PortImplementorInterface {
	Map <Coord, Color> getVisibleCoords(PlayerInterface player);
	Map <Coord, Color> getColorOfCoord(List<Coord> visibleCoordsBoat);
	boolean isInPort(Coord coord);
}
