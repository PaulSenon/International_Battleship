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

	public int idMine;
	public Coord coord;
	public int idPlayer;
	public StateMine state;

    public ProcessedProps(int idMine, Coord coord, int idPlayer, StateMine state){
        this.idMine = idMine;
        this.coord = coord;
        this.idPlayer = idPlayer;
        this.state = state;
    }
    
    public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getIdMine() {
		return idMine;
	}

	public Coord getCoord() {
		return coord;
	}

	public int getIdPlayer() {
		return idPlayer;
	}

	public StateMine getState() {
		return state;
	}

}