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

	void specialAction(Coord coordSquare);

    List<Coord> getVisibleCoords(Player player);

    List<PlayerInterface> getPlayers();

}
