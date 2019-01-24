package model;

import tools.*;

import java.util.List;

public interface BoatInterface {
    Pair<ResultShoot, ProcessedPosition> shoot(Coord target) throws Exception;

    void move(Coord destCoord);
    void moveHard(Coord destCoord);

    boolean hasCoord(Coord coord);

    List<Coord> getCoords();
    List<Coord> getCoordsForDirection(Direction direction);
    boolean isMoveOk(Coord coord);

    Coord getPivot();

    Direction getDirection();

    void setFacingDirection(Direction direction);

    void setLastPosition(Coord pivot);

	int getSize();

    void undoLastMove();
    ProcessedPosition getProcessedPosition();

    void rotateClockWise();
    void rotateCounterClockWise();

    int getNbFrontParts();
    int getNbBackParts();

    BoatType getType();

    int getId();

    int getMoveCost(int distance);
    int getRotateCost();
    int getShootCost();
    int getSpecialActionCost();

    int getPlayerId();

    List<Coord> getVisibleCoords();

	void actionSpecial(Coord coordSquare);

	Coord getCoord();

	void destroy();

	boolean getDestroy();

    boolean canMove();

    void hasMoved();

    void moveAutorization();

	SpecialActionInterface getSpecialAction();

    void setProcessedPosition(ProcessedPosition processedPosition);

	void setDammage(int nbDammage) throws Exception;
	
	List<Integer> getTouchedFragmentIds();
	
	Coord getCoordBehind();

    boolean getMove();
}
