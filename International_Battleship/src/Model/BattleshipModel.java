package Model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("2d5b787d-2269-4d70-9e4e-dd727dfa9336")
public class BattleshipModel implements BattleshipGameModel {
    @objid ("873f53fc-6221-4e2d-bc75-1d8495bf8ce6")
    public BattleshipImplementor battleshipImplementor;

    @objid ("245404cb-acb3-41d4-b19d-5717b51a8f66")
    public BattleshipModel() {
    	battleshipImplementor = new BattleshipImplementor();
    }
    
    @objid ("2ae2da19-fa1b-4a19-bb09-b4aa95f3903c")
    public void getColorCurrentPlayer() {
    }

    @objid ("fa9a7c83-14a9-4a47-8786-28afdc857cac")
    public void move() {
    }

    @objid ("0afc1bfb-1667-4d42-92d9-745fb5663841")
    public void isEnd(){
    	
    }
}
