package model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.Coord;
import tools.Pair;
import tools.ProcessedPosition;

import tools.ResultShoot;

import java.util.List;
import java.util.Map;

@objid ("82d664fa-9274-4766-b754-b991176fe06c")
public interface GameModelInterface {
    @objid ("46dbd90a-2313-4ab2-b3c0-b29def0c9d69")
    void getColorCurrentPlayer();

    @objid ("b23fa4cb-4a24-4e7f-a704-671894685a3e")
    ProcessedPosition moveBoat(int xDest, int yDest);

    ProcessedPosition rotateBoatClockWise();

    ProcessedPosition rotateBoatCounterClockWise();

    ProcessedPosition selectBoat(int x, int y);

    @objid ("70b98e64-650c-4441-80ad-81c55eb78c6d")
    void isEnd();

    Pair<ResultShoot, ProcessedPosition> shoot(Coord target);

    Map<BoatName, ProcessedPosition> getListOfBoat();

    List<Coord> getVisibleCoords(Player player);

    List<PlayerInterface> getPlayers();

}
