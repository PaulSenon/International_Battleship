package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import tools.Coord;

public interface PortInterface extends Serializable{
	int getPlayerIdInModel();
	Color getColor();
	Map <Coord, Color> getVisibleCoords();
}
