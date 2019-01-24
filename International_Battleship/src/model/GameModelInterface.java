package model;


import model.exceptions.SelectBoatException;
import tools.*;

import java.awt.Color;
import java.util.List;
import java.util.Map;

public interface GameModelInterface {
	PlayerInterface getCurrentPlayer();

    ProcessedPosition moveBoat(int xDest, int yDest);

    ProcessedPosition rotateBoatClockWise();

    ProcessedPosition rotateBoatCounterClockWise();

    ProcessedPosition selectBoat(int x, int y) throws SelectBoatException;

    void isEnd();

    ProcessedPosition shoot(Coord target);

    Map<Integer, ProcessedPosition> getListOfBoat();
    
    Map<Integer, ProcessedProps> getListOfMine();

    int getApCurrentPlayer();

    List<Coord> getVisibleCoords(PlayerInterface player);

    List<Coord> getVisibleCoordsCurrentPlayer();

    List<BoatInterface> getVisibleBoats(PlayerInterface player);

    List<BoatInterface> getVisibleBoatsCurrentPlayer();

	List<ProcessedPosition> specialAction(Coord coordSquare);

	void EndActionsOfPlayer();

	void initTurn();

    void endTurn();

    void initDay();

    void endDay();

    void nextPlayer();

    void unselectBoat();

    boolean hasSelectedBoat();

    PlayerInterface createPlayer(int idPlayer);

    void setProcessedPosition(ProcessedPosition processedPosition);

    void setupGame();

    PlayerInterface getClientPlayer();

    boolean itsTurn();

    Map <Coord, Color>  getPortsCoordsCurrentPlayer();

    Map <Coord, Color>  getPortsCoords(PlayerInterface clientPlayer);

	Map<Integer, Integer> getBoatsAndPlayersId();

    boolean canCurrentBoatShoot();

    boolean canCurrentBoatRotate();

    boolean canCurrentBoatDoSpecialAction();

    List<ProcessedProps> getProcessedPropsToUpdate();

    boolean isInstantActionForCurrentBoat();

    void setProcessedPosition(ProcessedProps processedProps);
}
