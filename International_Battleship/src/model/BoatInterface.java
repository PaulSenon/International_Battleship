package model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.*;

import java.util.List;

@objid ("c1e77788-c863-45cf-bace-9f86e3b2f0cc")
public interface BoatInterface {
    @objid ("83fb710c-9713-4424-ae50-2f2fa0c5e14b")
    Pair<ResultShoot, ProcessedPosition> shoot(Coord target) throws Exception;

    @objid ("40a00f44-2a87-4bdf-b47b-15f693d433c0")
    void move(Coord destCoord);
    void moveHard(Coord destCoord);

    boolean hasCoord(Coord coord);

    List<Coord> getCoords();
    List<Coord> getCoordsForDirection(Direction direction);
    boolean isMoveOk(Coord coord);

    Coord getPivot();

    Direction getDirection();

    void setLastPosition(Coord pivot);

	int getSize();

    void undoLastMove();
    ProcessedPosition getProcessedPosition();

    void rotateClockWise();
    void rotateCounterClockWise();

    int getNbFrontParts();
    int getNbBackParts();

    BoatName getName();

    int getId();

    List<Coord> getVisibleCoords();
}
