package View;

import java.util.List;

import Model.Coord;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("abcfe3e6-1704-4c47-9836-89a464071031")
public interface BattleshipGameGUI {
    @objid ("b6685c0f-42eb-4133-8a48-503ea68e82c9")
    void setBoatToMove(Coord coord);

    @objid ("33b74766-09fc-4655-bd46-a8cbe70b73ed")
    void setVisibility();

    @objid ("fdfc29a2-a084-459f-a932-902ae3e95275")
    void movePiece(Coord targetCoord);

    @objid ("97f4b282-5a93-449d-b9e4-ba015488d026")
    void undoMovePiece(Coord boatToMoveInitCoord);

}
