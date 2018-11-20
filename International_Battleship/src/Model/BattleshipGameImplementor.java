package Model;
import tools.ResultShoot;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.Coord;
import tools.Direction;

import View.BattleShipSquareGUI;

@objid ("75a7604f-f615-4013-af67-b5d5bd901738")
public interface BattleshipGameImplementor {
    @objid ("292fef80-59e0-4f72-99c4-35036676c47b")
    ResultShoot shoot(Coord target);

    @objid ("4643b543-6571-4c67-bf46-c267384eea71")
    Boat findBoatByCoord(Coord coord);

    @objid ("37d29d69-fc60-40fa-81d7-2d4a1000d1e2")
    Coord move(Boat selectedBoat, Coord destination);

    // TODO move direction in tools
    Direction rotateBoatClockWise(Boat selectedBoat);

    // TODO move direction in tools
    Direction rotateBoatCounterClockWise(Boat selectedBoat);

}
