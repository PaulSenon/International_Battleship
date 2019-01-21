package model;

import tools.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MineImplementor implements MineImplementorInterface{
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
		ProcessedPropsManager.addToQueue(mine.getProcessedProps());
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
		for(Mine mine: this.mines){
			if(mine.getCoord().equals(coord)){
				this.mines.remove(mine);
				mine.destroy();
				ProcessedPropsManager.addToQueue(mine.getProcessedProps());
				return;
			}
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

	@Override
	public void processMineProcessedProps(ProcessedProps processedProps) {
		if(processedProps == null || processedProps.type != PropsType.MINE) return;

		switch (processedProps.stateMine){
			case ALIVE:
				Mine mine = new Mine(processedProps.coord,processedProps.idMine,processedProps.idPlayer,processedProps.visibleRadius);
				this.mines.add(mine);
				break;
			case DESTROY:
				for (Mine mine2: this.mines) {
					if(mine2.getIdMine() == processedProps.idMine){
						this.mines.remove(mine2);
						mine2.destroy();
						break;
					}
				}
				break;
		}
	}


}
