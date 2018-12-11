package model;


import tools.Coord;
import tools.Pair;
import tools.ProcessedPosition;
import tools.ResultShoot;

import java.util.Map;
import java.util.Observable;

public class GameModelObs extends Observable implements GameModelInterface {


    // TODO POURQUOI ON A UN REF à UN GameModelInterface ALORS QUE CETTE CLASSE ETEND GameModelInterface ?


        public GameModelInterface battleshipModel;
    
    public GameModelObs(GameModelInterface battleshipModel) {
		super();
		this.battleshipModel = new GameModel();
	}

    @Override
    public PlayerInterface getCurrentPlayer() {
        return null;
    }

    @Override
    public ProcessedPosition moveBoat(int xDest, int yDest) {
        return null;
    }

    @Override
    public ProcessedPosition rotateBoatClockWise() {
        return null;
    }

    @Override
    public ProcessedPosition rotateBoatCounterClockWise() {
        return null;
    }

        public void move() {
    }

        public void isEnd() {
    	
    }

    @Override
    public ProcessedPosition selectBoat(int x, int y) {
        // TODO dégueulasse
        return null;
    }

    @Override
    public Map<BoatName, ProcessedPosition> getListOfBoat() {
        return null;
    }

    @Override
	public Pair<ResultShoot, ProcessedPosition> shoot(Coord target) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
