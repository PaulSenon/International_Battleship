package model;

import tools.Coord;
import tools.Pair;
import tools.ProcessedPosition;
import tools.ResultShoot;

import java.util.List;
import java.util.Map;

public interface BoatsImplementorInterface {

    BoatInterface findBoatByCoord(Coord coord);

    ProcessedPosition moveBoat(PlayerInterface currentPlayer, BoatInterface selectedBoat, Coord destination);

    ProcessedPosition rotateBoat(PlayerInterface currentPlayer, BoatInterface selectedBoat, boolean clockWise);

    Pair<ResultShoot, ProcessedPosition> shootBoat(PlayerInterface currentPlayer, BoatInterface selectedBoat, Coord target);

    ProcessedPosition undoLastBoatMove(BoatInterface selectedBoat);

    Map<Integer, ProcessedPosition> getBoats();

    int findPlayerIdFromBoat(BoatInterface boat);

    List<Coord> getVisibleCoords(PlayerInterface player);
}
