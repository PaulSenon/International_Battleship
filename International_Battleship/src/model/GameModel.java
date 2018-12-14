package model;


import tools.*;

import java.util.*;

public class GameModel implements GameModelInterface {

    // The implementor use to manage boats
    private BoatsImplementorInterface battleshipImplementor;

    // The implementor use to manage players
    private PlayersImplementorInterface playersImplementor;

    // The selected boat (may be null)
    private BoatInterface selectedBoat;
    private PlayerInterface currentPlayer;

    /**
     * __CONSTRUCTOR__
     */
    public GameModel() {
        // Create UID static instance
        UniqueIdGenerator.newInstance();

        this.selectedBoat = null;

        try {
            playersImplementor = new PlayersImplementor(GameConfig.getPlayers());
            battleshipImplementor = new BoatsImplementor(playersImplementor.getPlayers());
            // TODO debug, to set the first player... May be not clean...
            this.currentPlayer = playersImplementor.getPlayers().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        public ProcessedPosition moveBoat(int xDest, int yDest) {
        // error case :
        if(this.selectedBoat == null){
            // TODO just placeholder yet.
            System.out.println("No boat has been selected");
            return null;
        }
        // processing :
        Coord destCoord = new Coord(xDest, yDest);
        ProcessedPosition processedPosition = this.battleshipImplementor.moveBoat(this.currentPlayer, this.selectedBoat, destCoord);

        // regarder si toutes les coords sont ok (sortie de plateau && déclanchement mines)
        if(this.isNewPosOk(processedPosition.coords)){
            return processedPosition;
        }else {
            this.playersImplementor.undoLastMove(this.currentPlayer);
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
    private ProcessedPosition rotateSelectedBoat(boolean clockWise) {
        // error case :
        if(this.selectedBoat == null){
            // TODO just placeholder yet.
            System.out.println("No boat has been selected");
            return null;
        }

        // processing : boat rotation
        ProcessedPosition processedPosition = this.battleshipImplementor.rotateBoat(this.currentPlayer, this.selectedBoat, clockWise);

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
        BoatInterface boat = this.battleshipImplementor.findBoatByCoord(coord);
        if(boat == null){
            System.out.println("There is no boat here");
            return null;
        }
        // Verify that the boat belongs to the current player
        if(this.currentPlayer.getId() == this.battleshipImplementor.findPlayerIdFromBoat(boat)){
            this.selectedBoat = boat;
        }else{
            System.out.println("The boat you selected doesn't belong to you !");
        }

        // tell if it doing great or not
        // TODO it might by undefined, do tests
        try {
            return this.selectedBoat.getProcessedPosition();
        }catch (Exception e){
            return null;
        }
    }

    /**
     *
     */
        public void isEnd(){
    }

    /**
     *
     * @return
     */
    private List<BoatType> DEBUG_get_test_fleet_from_enum(){
    	List<BoatType> fleetList = new LinkedList<>();
        Collections.addAll(fleetList, BoatType.values());
    	return fleetList;
    }

    /**
     *
     * @return
     */
    public PlayerInterface getCurrentPlayer() {
            return this.currentPlayer;
    }

    /**
     *
     * @param currentPlayer
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


    /**
     *
     * @param target
     * @return
     */
    @Override
	public Pair<ResultShoot, ProcessedPosition> shoot(Coord target) {
        // error case :
        if(this.selectedBoat == null){
            // TODO just placeholder yet.
            System.out.println("No boat has been selected");
            return null;
        }
		return battleshipImplementor.shootBoat(this.currentPlayer, this.selectedBoat, target);
	}

    public Map<Integer, ProcessedPosition> getListOfBoat(){
        return this.battleshipImplementor.getBoats();
    }

    @Override
    public int getApCurrentPlayer() {
        return this.currentPlayer.getNbActionPoint();
    }

    public List<Coord> getVisibleCoords(PlayerInterface player) {
        return this.battleshipImplementor.getVisibleCoords(player);
    }

    public List<Coord> getVisibleCoordsCurrentPlayer() {
        return this.getVisibleCoords(this.currentPlayer);
    }

}
