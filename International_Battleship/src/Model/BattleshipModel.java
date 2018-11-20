package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.Coord;
import tools.Direction;

import View.BattleShipSquareGUI;
import tools.ResultShoot;

@objid ("2d5b787d-2269-4d70-9e4e-dd727dfa9336")
public class BattleshipModel implements BattleshipGameModel {

    // The implementor use to manage boats
    @objid ("873f53fc-6221-4e2d-bc75-1d8495bf8ce6")
    private BattleshipGameImplementor battleshipImplementor;

    // player list
    private List<Player> players;

    // The selected boat (may be null)
    private Boat selectedBoat;

    /**
     * __CONSTRUCTOR__
     */
    @objid ("245404cb-acb3-41d4-b19d-5717b51a8f66")
    public BattleshipModel() {
        // set attributes
        this.players = new ArrayList<>();
        this.selectedBoat = null;
    	battleshipImplementor = new BoatsImplementor(this.players, this.DEBUG_get_test_fleet_from_enum());
    }

    /**
     * TO BE CALLED FROM CONTROLLER
     *
     * Move the selectedBoat to the desired position if possible.
     * You must select a boat before calling this method
     *
     * @param xDest is the desired destination x coordinate on the game board
     * @param yDest is the desired destination y coordinate on the game board
     * @return Coord is the pivot coordinate where the boat is after processing (may no change)
     */
    @objid ("fa9a7c83-14a9-4a47-8786-28afdc857cac")
    public Coord moveBoat(int xDest, int yDest) {
        // error case :
        if(this.selectedBoat == null){
            // TODO just placeholder yet.
            System.out.println("No boat has been selected");
            return null;
        }

        // processing :
        Coord destCoord = new Coord(xDest, yDest);
        // TODO tests si cout déplacement ok
        // TODO tests si trajectoire ok (pas de mine...)
        return this.battleshipImplementor.move(this.selectedBoat, destCoord);
    }

    /**
     * TO BE CALLED FROM CONTROLLER
     *
     * Rotate the selectedBoat clock wise.
     * You must select a boat before calling this method.
     *
     * @return Direction is the direction of the boat after processing (may not change)
     */
    public Direction rotateBoatClockWise() {
        // error case :
        if(this.selectedBoat == null){
            // TODO just placeholder yet.
            System.out.println("No boat has been selected");
            return null;
        }

        // processing :
        // TODO demander au boatImplementor la list des coords potentielels (sans effectuer le déplacement)
        // TODO     => et regarder si toutes les coords sont ok (sortie de plateau && déclanchement mines
        return this.battleshipImplementor.rotateBoatClockWise(this.selectedBoat);
    }

    /**
     * TO BE CALLED FROM CONTROLLER
     *
     * Rotate the selectedBoat COUNTER clock wise.
     * You must select a boat before calling this method.
     *
     * @return Direction is the direction of the boat after processing (may not change)
     */
    public Direction rotateBoatCounterClockWise() {
        // error case :
        if(this.selectedBoat == null){
            // TODO just placeholder yet.
            System.out.println("No boat has been selected");
            return null;
        }

        // processing :
        // TODO demander au boatImplementor la list des coords potentielels (sans effectuer le déplacement)
        // TODO     => et regarder si toutes les coords sont ok (sortie de plateau && déclanchement mines
        return this.battleshipImplementor.rotateBoatCounterClockWise(this.selectedBoat);
    }

    /**
     * TO BE CALLED FROM CONTROLLER
     *
     * This method tell the model to set its selectedBoat instance
     * if there is one at the given coordinates
     *
     * NOTE that we get raw x,y that we transform into Coord.
     *      it's to separate the model from the other stuff (controller and view)
     *
     * @param x is the x coordinate on the game board
     * @param y is the y coordinate on the game board
     * @return boolean (to tell if it's doing well or not)
     */
    public boolean selectBoat(int x, int y){
        // transform into coord to use through model
        Coord coord = new Coord(x, y);

        // set the selected boat (may return null)
        this.selectedBoat = this.battleshipImplementor.findBoatByCoord(coord);

        // tell if it doing great or not
        return this.selectedBoat != null;
    }

    @objid ("0afc1bfb-1667-4d42-92d9-745fb5663841")
    public void isEnd(){

    }

    private List<BoatName> DEBUG_get_test_fleet_from_enum(){
    	List<BoatName> fleetList = new LinkedList<>();
        Collections.addAll(fleetList, BoatName.values());
    	return fleetList;
    }

    @objid ("2ae2da19-fa1b-4a19-bb09-b4aa95f3903c")
    public void getColorCurrentPlayer() {
        // TODO Auto-generated stub
    }

	@Override
	public ResultShoot shoot(Coord target) {
		return ResultShoot.MISSED;
//		return (battleshipImplementor.shoot(target));
	}

}
