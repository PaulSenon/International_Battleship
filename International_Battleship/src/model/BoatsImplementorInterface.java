package model;

import tools.Coord;
import tools.Pair;
import tools.ProcessedPosition;
import tools.ProcessedProps;
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

    List<BoatInterface> getVisibleBoats(PlayerInterface player);

	List<Pair<ResultShoot, ProcessedPosition>> specialAction(PlayerInterface currentPlayer, BoatInterface selectedBoat, Coord target);

	BoatInterface findBoayById(int boatId);

	int getRemainsBoatsByPlayer(int playerId);

    List<BoatInterface> getPlayerFleet(PlayerInterface player);

    void setProcessedPosition(ProcessedPosition processedPosition);

	Map<Integer, Integer> getBoatsAndPlayersId();

    void createMine(PlayerInterface currentPlayer, BoatInterface selectedBoat);
}
