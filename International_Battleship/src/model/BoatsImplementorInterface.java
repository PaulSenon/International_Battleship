package model;

import tools.Coord;
import tools.Pair;
import tools.ProcessedPosition;
import tools.ResultShoot;

import java.util.Map;

public interface BoatsImplementorInterface {
        Pair<ResultShoot, ProcessedPosition> shootBoat(Coord target);

        BoatInterface findBoatByCoord(Coord coord);

        ProcessedPosition moveBoat(BoatInterface selectedBoat, Coord destination);

    ProcessedPosition rotateBoat(BoatInterface selectedBoat, boolean clockWise);

    ProcessedPosition undoLastBoatMove(BoatInterface selectedBoat);

    Map<BoatName, ProcessedPosition> getBoats();
}
