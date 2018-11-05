package Model;

import java.util.ArrayList;
import java.util.List;
import Model.BoatsImplementor;
import Model.Coord;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("2d5b787d-2269-4d70-9e4e-dd727dfa9336")
public class BattleshipModel implements BattleshipGameModel {
    @objid ("873f53fc-6221-4e2d-bc75-1d8495bf8ce6")
    public BattleshipGameImplementor battleshipImplementor;

    @objid ("245404cb-acb3-41d4-b19d-5717b51a8f66")
    public BattleshipModel() {
    	battleshipImplementor = new BoatsImplementor("Sentinel");
    }
    
    @objid ("2ae2da19-fa1b-4a19-bb09-b4aa95f3903c")
    public void getColorCurrentPlayer() {
    }

    @objid ("fa9a7c83-14a9-4a47-8786-28afdc857cac")
    public String move(int xInit, int yInit, int xFinal, int yFinal) {
		String at = null;
    	List<Coord> coord = new ArrayList<Coord>();
		if(xInit != xFinal || yInit != yFinal){
	    	battleshipImplementor.setSelectedPiece(battleshipImplementor.findPiece(xInit, yInit));
			if (battleshipImplementor.getSelectedPiece() != null){
				coord = battleshipImplementor.getSelectedPiece().getMoveItinerary(xFinal,yFinal);
				Coord target = battleshipImplementor.checkReachable(coord, battleshipImplementor.getSelectedPiece());
				if(target != null){
					xFinal = target.getX();
					yFinal = target.getY();
					if(battleshipImplementor.getSelectedPiece().isAlgoMoveOk(xFinal, yFinal)){
						at = battleshipImplementor.manageCatch(xFinal, yFinal);
						if(at == "ILLEGAL"){
							battleshipImplementor.getSelectedPiece().doMove(xFinal, yFinal);
						}
					}
					else{
						at = "ILLEGAL";
					}
				}
				else{
					at = "ILLEGAL";
				}
			}
		}
		return at;
    }

    @objid ("0afc1bfb-1667-4d42-92d9-745fb5663841")
    public void isEnd(){
    	
    }

	@Override
	public List<Coord> getPieceListMoveOK(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
}
