package model;

import java.util.ArrayList;
import java.util.List;

import tools.Coord;
import tools.UniqueIdGenerator;

public class MineImplementor {
	private List<Mine> mines;

	public MineImplementor(){
		this.mines = new ArrayList<>();
	}
	
	public void createMine(Coord coordMine, int idPlayer){
		if (this.isMined(coordMine)) {
			System.out.println("Attention une mine existe déjà à cet endroit");
			return;
		}
		Mine mine = new Mine(coordMine,UniqueIdGenerator.getNextId(),idPlayer,0);
		this.mines.add(mine);
	}
	
	public boolean isMined(Coord coordMine){
		for (Mine mine : mines) {
			if(mine.getCoord().equals(coordMine)){
				return true;
			}
		}
		return false;
	}
	
	public List<Coord> getVisibleCoords(int playerId){
		List<Coord> visibleCoords = new ArrayList<>();
		for (Mine mine : mines) {
			if(mine.getIdPlayer() == playerId){
				visibleCoords.addAll(mine.getVisibleCoords());
			}
		}
		return visibleCoords;
	}
	
	public void destroyMine(Coord coord){
		if(isMined(coord)){
			for (int i = 0; i < mines.size(); i++) {
				if (mines.get(i).getCoord().equals(coord)) {
					mines.remove(i);
					break;
				}
			}
		}
		else{
			System.out.println("Suppression impossible car il n'y a pas de mine aux coordonnées indiquées");
		}
	}
}
