package Controler;

import Model.Coord;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("0ba7d808-0e11-42c8-9e3f-0f056a59efa6")
public interface BattleshipGameControlerModel {
    @objid ("b916c4ea-3f0d-4a34-bb89-f59aecdd6ca1")
    void ActionWhenBoatIsSelectedOnGUI(Coord pieceToMoveCoord);

    @objid ("3b3496e7-3c7b-4770-8043-d0674ed20fc7")
    void ActionWhenBoatIsMovedOnGUI(Coord pieceToMoveCoord,Coord targetCoord);

}
