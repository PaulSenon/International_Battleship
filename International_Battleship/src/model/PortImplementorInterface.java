package model;

import tools.Coord;

import java.awt.*;
import java.util.List;
import java.util.Map;

public interface PortImplementorInterface {
	Map <Coord, Color> getVisibleCoords(PlayerInterface player);
	Map <Coord, Color> getColorOfCoord(List<Coord> visibleCoordsBoat);
	boolean isInPort(Coord coord);

    boolean checkIfCoordInEnnemyPort(Coord coord, int playerId);
}
