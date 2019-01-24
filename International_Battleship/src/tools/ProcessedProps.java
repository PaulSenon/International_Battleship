package tools;

import java.io.Serializable;


/**
 * This class is used to transfer a whole mine position information
 * to the view to allow drawing it.
 *
 * TODO this class may change, feel free to add some other properties if necessary
 */
public class ProcessedProps implements Serializable{
    static final long serialVersionUID = 6734671468768712L;

	public PropsType type;

	public int idMine;
	public Coord coord;
	public int idPlayer;
	public StateMine stateMine;
	public FxType typeFx;
	public int visibleRadius;

    public ProcessedProps(PropsType type, int idMine, Coord coord, int idPlayer, StateMine stateMine, int visibleRadius){
    	this.type = type;
        this.idMine = idMine;
        this.coord = coord;
        this.idPlayer = idPlayer;
        this.stateMine = stateMine;
        this.visibleRadius = visibleRadius;
    }

    public ProcessedProps(PropsType type, Coord coord, FxType typeFx){
    	this.type = type;
    	this.coord = coord;
    	this.typeFx = typeFx;
	}

}