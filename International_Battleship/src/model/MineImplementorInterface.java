package model;

import java.util.List;

import tools.Coord;
import tools.ProcessedProps;

public interface MineImplementorInterface {
	public ProcessedProps createMine(Coord coordMine, int idPlayer);
	
	public boolean isMined(Coord coordMine);
	
	public List<Coord> getVisibleCoords(int playerId);
	
	public ProcessedProps destroyMine(Coord coord);
}
