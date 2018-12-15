package model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@objid ("2d5b787d-2269-4d70-9e4e-dd727dfa9336")
public class GameModel implements GameModelInterface {

    // The implementor use to manage boats
    @objid ("873f53fc-6221-4e2d-bc75-1d8495bf8ce6")
    private BoatsImplementorInterface battleshipImplementor;

    // player list
    private List<PlayerInterface> players;

    // The selected boat (may be null)
    private BoatInterface selectedBoat;

    AtomicInteger atomicInteger;

    /**
     * __CONSTRUCTOR__
     */
    @objid ("245404cb-acb3-41d4-b19d-5717b51a8f66")
    public GameModel() {
        //Set the ids for boats
        this.atomicInteger = new AtomicInteger();
        // set attributes
        this.players = new ArrayList<>();
        //For testing the creation of player in model in the setting of the GameModel
        try {
            createPlayer("Player1", "Port1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.selectedBoat = null;
    	//battleshipImplementor = new BoatsImplementor(this.players, this.DEBUG_get_test_fleet_from_enum());
        battleshipImplementor = new BoatsImplementor(this.players);
    }

    /**
     * // TODO Tests
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
    public ProcessedPosition moveBoat(int xDest, int yDest) {
        // error case :
        if(this.selectedBoat == null){
            // TODO just placeholder yet.
            System.out.println("No boat has been selected");
            return null;
        }

        // processing :
        Coord destCoord = new Coord(xDest, yDest);
        ProcessedPosition processedPosition = this.battleshipImplementor.moveBoat(this.selectedBoat, destCoord);

        // regarder si toutes les coords sont ok (sortie de plateau && déclanchement mines)
        if(this.isNewPosOk(processedPosition.coords)){
            return processedPosition;
        }else{
            processedPosition = this.battleshipImplementor.undoLastBoatMove(this.selectedBoat);
            return processedPosition;
        }
    }

    /**
     * __TESTED__
     * TO BE CALLED FROM CONTROLLER
     *
     * Rotate the selectedBoat clock wise.
     * You must select a boat before calling this method.
     *
     * @return ProcessedPositions (coords + direction)
     */
    public ProcessedPosition rotateBoatClockWise() {
        return this.rotateSelectedBoat(true);
    }

    /**
     * __TESTED__
     * TO BE CALLED FROM CONTROLLER
     *
     * Rotate the selectedBoat counter clock wise.
     * You must select a boat before calling this method.
     *
     * @return ProcessedPositions (coords + direction)
     */
    public ProcessedPosition rotateBoatCounterClockWise() {
        return this.rotateSelectedBoat(false);
    }

    /**
     * __TESTED__
     *
     * Rotate the selectedBoat.
     *
     * @return ProcessedPositions (coords + direction)
     */
    private ProcessedPosition rotateSelectedBoat(boolean clockWise){
        // error case :
        if(this.selectedBoat == null){
            // TODO just placeholder yet.
            System.out.println("No boat has been selected");
            return null;
        }

        // processing : boat rotation
        ProcessedPosition processedPosition = this.battleshipImplementor.rotateBoat(this.selectedBoat, clockWise);

        // regarder si toutes les coords sont ok (sortie de plateau && déclanchement mines)
        if(this.isNewPosOk(processedPosition.coords)){
            return processedPosition;
        }else{
            processedPosition = this.battleshipImplementor.undoLastBoatMove(this.selectedBoat);
            return processedPosition;
        }
    }

    /**
     * TODO
     * __PARTIALLY_TESTED__
     *
     * @param coords list of coords to check
     * @return is coords are accessible
     */
    private boolean isNewPosOk(List<Coord> coords){
        // TODO, just a placeholder yet
        // TODO     => handle mine collision
        for(Coord coord : coords){
            if( // check out of bounds
                    coord.getX()< 0
                    || coord.getY() < 0
                    || coord.getX() > GameConfig.getGameGridWidth() - 1
                    || coord.getY() > GameConfig.getGameGridHeight() -1
            ){
                return false;
            }
        }
        return true;
    }

    /**
     * // TODO Tests
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
    public ProcessedPosition selectBoat(int x, int y){
        // transform into coord to use through model
        Coord coord = new Coord(x, y);

        // set the selected boat (may return null)
        this.selectedBoat = this.battleshipImplementor.findBoatByCoord(coord);

        // tell if it doing great or not
        // TODO it might by undefined, do tests
        try {
            return this.selectedBoat.getProcessedPosition();
        }catch (Exception e){
            return null;
        }
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
	public Pair<ResultShoot, ProcessedPosition> shoot(Coord target) {
        // error case :
        if(this.selectedBoat == null){
            // TODO just placeholder yet.
            System.out.println("No boat has been selected");
            return null;
        }
        return battleshipImplementor.shootBoat(target);
	}

    public Map<BoatName, ProcessedPosition> getListOfBoat(){
        return this.battleshipImplementor.getBoats();
    }

    /**
     * Create a player and add it to the list of players
     * @param name
     * @param port
     */
	public void createPlayer(String name, String port) throws Exception{
        for (PlayerInterface player : players){
            if (player.getName().equals(name) || player.getPortName().equals(port)){
                throw new Exception("Player or Port already existing");
            }
        }
	    Player player = new Player(name, port);
        //TODO : Remove the following lines, it's just for DEBUG
        List<BoatName> fleetList = new LinkedList<>();
        Collections.addAll(fleetList, BoatName.values());
        int i = 5;
        for (BoatName boat : fleetList){
            addBoat(player,boat,new Coord(5,i));
            i++;
        }
        //addBoat(player,BoatName.Sentinel, new Coord(2,2));
        //addBoat(player,BoatName.TorpedoBoat, new Coord(4,4));
        //addBoat(player,BoatName.Submarin, new Coord(6,6));
        //addBoat(player,BoatName.Cruiser, new Coord(9,9));
        //addBoat(player,BoatName.AircraftCarrier, new Coord(13,13));
        //END of lines for DEBUG
        this.players.add(player);
    }

    public void addBoat(Player player, BoatName boatName, Coord coord){
	    int id = this.atomicInteger.getAndIncrement();
	    player.addBoatInFleet(boatName, coord, id);
    }

    /**
     * Delete a player thanks to its name and delete it from the list of the players in the GameModel
     * @param name
     * @return result of the destruction of the player
     */
    public void deletePlayer(String name) throws Exception{
        PlayerInterface toDestroy = null;
        for (PlayerInterface player : this.players){
            if (player.getName().equals(name)) {
                toDestroy = player;
                //TODO: delete object player
            }
        }
        if (toDestroy != null) {
            this.players.remove(toDestroy);
        }
        else {
            throw new Exception("The player you want to delete isn't existing");
        }
    }

    public List<PlayerInterface> getPlayers() {
        return this.players;
    }

    public List<Coord> getVisibleCoords(Player player) {
        return this.battleshipImplementor.getVisibleCoords(player);
    }

}
