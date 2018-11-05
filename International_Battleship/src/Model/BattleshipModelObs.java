package Model;

import java.util.List;
import java.util.Observable;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("8e40e397-dd7f-4d77-b403-4842a42c93f1")
public class BattleshipModelObs extends Observable implements BattleshipGameModel {


    // TODO POURQUOI ON A UN REF à UN BattleshipGameModel ALORS QUE CETTE CLASSE ETEND BattleshipGameModel ?


    @objid ("6ed5693f-ad1f-4ed4-bb92-f3b98a59fb76")
    public BattleshipGameModel battleshipModel;
    
    public BattleshipModelObs() {
		super();
		this.battleshipModel = new 	BattleshipModel();
	}

	@objid ("7e8e12ff-249d-4017-80c7-a48eedb75d23")
    public void getColorCurrentPlayer() {
    }

    @objid ("73f8ce41-88de-4db0-a003-adc06d04a60b")
    public void move() {
    }

    @objid ("b3c114e7-46d1-4a99-bcf4-ae1c05b82a35")
    public void isEnd() {
    	
    }

	@Override
	public String move(int xInit, int yInit, int xFinal, int yFinal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coord> getPieceListMoveOK(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

}
