package model;


import tools.Coord;
import tools.Pair;
import tools.ProcessedPosition;
import tools.ResultShoot;

import java.util.List;
import java.util.Observable;

public abstract class GameModelObs extends Observable implements GameModelInterface {

	@Override
	public void EndActionsOfPlayer() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BoatInterface> getVisibleBoats(PlayerInterface player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoatInterface> getVisibleBoatsCurrentPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initTurn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void endTurn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initDay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void endDay() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Pair<ResultShoot, ProcessedPosition>> specialAction(Coord coordSquare) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void nextPlayer() {
		// TODO Auto-generated method stub

	}

    @Override
    public boolean hasSelectedBoat() {
        return false;
    }

    @Override
    public PlayerInterface createPlayer(int idPlayer) {
        return null;
    }

    @Override
    public void setProcessedPotion(ProcessedPosition processedPosition) {

    }

    @Override
    public void setupGame() {

    }

}
