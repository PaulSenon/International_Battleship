package model;

import tools.Coord;
import tools.ProcessedPosition;
import tools.ProcessedProps;
import tools.StateMine;

import java.util.ArrayList;
import java.util.List;


public class Mine {
	private int idMine;
	private Coord coord;
	private int idPlayer;
	private int visibleRadius;
	
	public Mine(Coord coord, int idMine, int idPlayer, int visibleRadius){
		this.coord = coord;
		this.idMine = idMine;
		this.idPlayer = idPlayer;
		this.visibleRadius = visibleRadius;
	}
	
	public Coord getCoord() {
		return coord;
	}

	public int getIdPlayer() {
		return idPlayer;
	}

	public List<Coord> getVisibleCoords(){
        List<Coord> areaOfEffect = new ArrayList<>();
        int lineBeginning = this.coord.getY() - this.visibleRadius;
        int lineEnd = this.coord.getY() + this.visibleRadius + 1;
        int columnBeginning = this.coord.getX() - this.visibleRadius;
        int columnEnd = this.coord.getX() + this.visibleRadius + 1;
        if(this.visibleRadius == 0){
        	areaOfEffect.add(this.coord);
        }else{
	        for (int line = lineBeginning; line<lineEnd; line ++){
	            for(int column = columnBeginning; column<columnEnd; column ++) {
	                if (!((line==lineBeginning && column==columnBeginning) || (line==lineBeginning && column==columnEnd - 1) || (line==lineEnd - 1 && column==columnBeginning) || (line==lineEnd - 1 && column==columnEnd - 1))){
	                    Coord visibleCoord = new Coord(column, line);
	                    areaOfEffect.add(visibleCoord);
	                }
	            }
	        }
        }
        return areaOfEffect;
    }
	
    /**
     * @return ProcessedProps
     */
    public ProcessedProps getProcessedProps(){
        return new ProcessedProps(this.idMine, this.coord, this.idPlayer, StateMine.ALIVE);
    }
    
	public int getIdMine() {
		return idMine;
	}

	public int getVisibleRadius() {
		return visibleRadius;
	}
}
