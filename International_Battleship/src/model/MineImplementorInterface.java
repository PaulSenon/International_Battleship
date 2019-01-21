package model;

import tools.Coord;
import tools.ProcessedProps;

import java.util.List;
import java.util.Map;

public interface MineImplementorInterface {
	void createMine(Coord coordMine, int idPlayer);
	
	boolean isMined(Coord coordMine);
	
	List<Coord> getVisibleCoords(int playerId);
	
	void destroyMine(Coord coord);

	Map<Integer, ProcessedProps> getListOfMines();

	void processMineProcessedProps(ProcessedProps processedProps);
}
