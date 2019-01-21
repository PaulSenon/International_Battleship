package model;

import tools.*;

import java.util.List;
import java.util.Map;

public interface BoatsImplementorInterface {

    BoatInterface findBoatByCoord(Coord coord);

    ProcessedPosition moveBoat(PlayerInterface currentPlayer, BoatInterface selectedBoat, Coord destination);

    ProcessedPosition rotateBoat(PlayerInterface currentPlayer, BoatInterface selectedBoat, boolean clockWise);

    ProcessedPosition shootBoat(PlayerInterface currentPlayer, BoatInterface selectedBoat, Coord target);

    ProcessedPosition undoLastBoatMove(BoatInterface selectedBoat);

    Map<Integer, ProcessedPosition> getBoats();

    int findPlayerIdFromBoat(BoatInterface boat);

    List<Coord> getVisibleCoords(PlayerInterface player);

    List<BoatInterface> getVisibleBoats(PlayerInterface player);

	List<ProcessedPosition> specialAction(PlayerInterface currentPlayer, BoatInterface selectedBoat, Coord target);

	BoatInterface findBoayById(int boatId);

	int getRemainsBoatsByPlayer(int playerId);

    List<BoatInterface> getPlayerFleet(PlayerInterface player);

    void setProcessedPosition(ProcessedPosition processedPosition);

	Map<Integer, Integer> getBoatsAndPlayersId();

    void createMine(PlayerInterface currentPlayer, BoatInterface selectedBoat);

    void generateRandomMines(int i);
}
