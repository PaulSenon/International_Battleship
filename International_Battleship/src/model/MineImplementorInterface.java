package model;

import java.util.List;
import java.util.Map;

import tools.Coord;
import tools.ProcessedProps;

public interface MineImplementorInterface {
	void createMine(Coord coordMine, int idPlayer);
	
	boolean isMined(Coord coordMine);
	
	List<Coord> getVisibleCoords(int playerId);
	
	void destroyMine(Coord coord);

	Map<Integer, ProcessedProps> getListOfMines();
	
	List<ProcessedProps> flushProcessedPropsMineToUpdate();
}
