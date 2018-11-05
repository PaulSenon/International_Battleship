package Controler;

import java.util.ArrayList;
import java.util.List;

import Model.Coord;
import Model.BattleshipGameModel;
import View.BattleShipGridGUI;

import com.modeliosoft.modelio.javadesigner.annotations.objid;


@objid ("876e9f9a-d77c-4a9d-809e-0854b8d95d55")
public class BattleShipControlerLocal implements BattleshipGameControlerModelView {

    private BattleshipGameModel gameModel;
    private BattleShipGridGUI gridGUI;

    @objid ("be0371df-d97b-409c-a49c-c194011d27a8")
    public BattleShipControlerLocal(BattleshipGameModel gameModel) {
        this.gameModel = gameModel;
        this.gridGUI = new BattleShipGridGUI(); // set latter
    }

    @objid ("c21b247b-b822-4b13-9953-43f65175301b")
    public void setGridPanel(BattleShipGridGUI gridGUI) {
        this.gridGUI = gridGUI;
	}

    @objid ("4139c98d-c1d0-4cbd-8f28-29dd759f090f")
    public void isPlayerOk() {

    }

	@Override
	public void ActionWhenBoatIsSelectedOnGUI(Coord boatToMoveCoord) {
		System.out.println("boatToMoveCoord :" + boatToMoveCoord);
		/*gridGUI.setBoatToMove(boatToMoveCoord);
		List<Coord> coords = new ArrayList<Coord>();
		coords = gameModel.getPieceListMoveOK(boatToMoveCoord.getX(),boatToMoveCoord.getY());
		gridGUI.resetLight(coords,true);
		System.out.println("La pièce selectionnée est à la position : ("  +boatToMoveCoord.getX() + ";" + boatToMoveCoord.getY()+")");*/
	}

	@Override
	public void ActionWhenBoatIsMovedOnGUI(Coord pieceToMoveCoord,Coord targetCoord) {
		String movement = null;
		if(targetCoord != null){
			gameModel.move(pieceToMoveCoord.getX(),pieceToMoveCoord.getY(),targetCoord.getX(),targetCoord.getY());
		}
		/*switch (movement){

		case "MOVE" :
			gridGUI.movePiece(targetCoord);
			break;
		case "TAKE" :
			gridGUI.movePiece(targetCoord);
			break;
		default://Illegal or Unknown
			gridGUI.undoMovePiece(pieceToMoveCoord);

		}*/
	}

}
