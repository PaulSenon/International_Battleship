package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tools.BoatFactory;
import tools.Coord;
import tools.Direction;
import tools.GameConfig;
import tools.ProcessedPosition;
import tools.ProcessedProps;
import tools.StateMine;
import tools.UniqueIdGenerator;

public class MineImplementor implements MineImplementorInterface{
	private List<Mine> mines;
	private List<ProcessedProps> processedPropsMineToUpdate;

	public MineImplementor(){
		this.mines = new ArrayList<>();
		this.processedPropsMineToUpdate = new ArrayList<>();
		//Create mine 
		this.createMine(new Coord(8,2), 1);
		this.createMine(new Coord(5,5), 1);
	}
	
	public void createMine(Coord coordMine, int idPlayer){
		if (this.isMined(coordMine)) {
			System.out.println("Attention une mine existe déjà à cet endroit");
			return;
		}
		Mine mine = new Mine(coordMine,UniqueIdGenerator.getNextId(),idPlayer,0);
		this.mines.add(mine);
		this.processedPropsMineToUpdate.add(mine.getProcessedProps());
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
			for (int i = 0; i < this.mines.size(); i++) {
				Mine mine = this.mines.get(i);
				if (mine.getCoord().equals(coord)) {
					this.mines.remove(i);
					ProcessedProps processedProps = new ProcessedProps(i, coord, mine.getIdPlayer(), StateMine.DESTROY);
					this.processedPropsMineToUpdate.add(processedProps);
				}
			}
		}
		else{
			System.out.println("Suppression impossible car il n'y a pas de mine aux coordonnées indiquées");
		}
	}

	@Override
	public Map<Integer, ProcessedProps> getListOfMines() {
        Map<Integer,ProcessedProps> mineInitPos = new HashMap<>();
        for (Mine mine: this.mines) {
            mineInitPos.put(mine.getIdMine(),mine.getProcessedProps());
        }
        return mineInitPos;
	}

	public List<ProcessedProps> flushProcessedPropsMineToUpdate() {
		List<ProcessedProps> tmp = new ArrayList<>(this.processedPropsMineToUpdate);
		this.processedPropsMineToUpdate.clear();
		return tmp;
	}
	
	
}
