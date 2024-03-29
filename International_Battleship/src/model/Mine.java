package model;

import tools.Coord;
import tools.ProcessedProps;
import tools.PropsType;
import tools.StateMine;

import java.util.ArrayList;
import java.util.List;


public class Mine {
	private int idMine;
	private Coord coord;
	private int idPlayer;
	private int visibleRadius;
	private StateMine state;
	
	public Mine(Coord coord, int idMine, int idPlayer, int visibleRadius){
		this.coord = coord;
		this.idMine = idMine;
		this.idPlayer = idPlayer;
		this.visibleRadius = visibleRadius;
		this.state = StateMine.ALIVE;
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

    public void destroy(){
		this.state = StateMine.DESTROY;
	}
	
    /**
     * @return ProcessedProps
     */
    public ProcessedProps getProcessedProps(){
        return new ProcessedProps(PropsType.MINE, this.idMine, this.coord, this.idPlayer, this.state, this.visibleRadius);
    }
    
	public int getIdMine() {
		return idMine;
	}

	public int getVisibleRadius() {
		return visibleRadius;
	}
}
