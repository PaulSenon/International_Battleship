package model;


import tools.*;

import java.util.List;
import java.util.Map;

public interface GameModelInterface {
    PlayerInterface getCurrentPlayer();

    ProcessedPosition moveBoat(int xDest, int yDest);

    ProcessedPosition rotateBoatClockWise();

    ProcessedPosition rotateBoatCounterClockWise();

    ProcessedPosition selectBoat(int x, int y);

    void isEnd();

    Pair<ResultShoot, ProcessedPosition> shoot(Coord target);

    Map<Integer, ProcessedPosition> getListOfBoat();

    int getApCurrentPlayer();

    List<Coord> getVisibleCoords(PlayerInterface player);

    List<Coord> getVisibleCoordsCurrentPlayer();
    
    List<BoatInterface> getVisibleBoats(PlayerInterface player);

    List<BoatInterface> getVisibleBoatsCurrentPlayer();

	void specialAction(Coord coordSquare);

	void EndActionsOfPlayer();
	
	void initTurn();
    
    void endTurn();
    
    void initDay();
    
    void endDay();
    
    void nextPlayer();

}
