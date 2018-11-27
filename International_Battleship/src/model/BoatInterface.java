package model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.*;

import java.util.List;

@objid ("c1e77788-c863-45cf-bace-9f86e3b2f0cc")
public interface BoatInterface {

    @objid ("40a00f44-2a87-4bdf-b47b-15f693d433c0")
    void move();

    @objid ("c10f1d8a-c319-4f3f-9875-2a11226fb3d8")
    void hourlyRotation();

    @objid ("b621313c-9077-4c05-abd7-160fac2282a7")
    void antiHourlyRotation();

    boolean hasCoord(Coord coord);

    List<CoordInfos> getCoords();
    List<CoordInfos> getCoordsForDirection(Direction direction);

    Coord getPivot();

    Direction getDirection();

	int getSize();

	void setPivot(final Coord value);
    void undoLastMove();
    ProcessedPosition getProcessedPosition();

    void rotateClockWise();
    void rotateCounterClockWise();

    ResultShoot shoot(Coord target);
}
