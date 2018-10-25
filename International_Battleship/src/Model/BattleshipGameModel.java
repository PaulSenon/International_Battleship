package Model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.Coord;
import tools.Direction;

import View.BattleShipSquareGUI;
import tools.ResultShoot;

@objid ("82d664fa-9274-4766-b754-b991176fe06c")
public interface BattleshipGameModel {
    @objid ("46dbd90a-2313-4ab2-b3c0-b29def0c9d69")
    void getColorCurrentPlayer();

    @objid ("b23fa4cb-4a24-4e7f-a704-671894685a3e")
    Coord moveBoat(int xDest, int yDest);

    Direction rotateBoatClockWise();

    Direction rotateBoatCounterClockWise();

    @objid ("70b98e64-650c-4441-80ad-81c55eb78c6d")
    void isEnd();

	ResultShoot shoot(Coord target);

    boolean selectBoat(int x, int y);

}
