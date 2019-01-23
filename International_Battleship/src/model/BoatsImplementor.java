package model;


import tools.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoatsImplementor implements BoatsImplementorInterface {

	private List<BoatInterface> boats;
	private MineImplementor mineImplementor;


    public BoatsImplementor(List<PlayerInterface> players) {
        this.boats = new ArrayList<>();
        this.generateBoatsFromFactory(players);
        this.mineImplementor = new MineImplementor();
    }

    /**
     * This method generates boat from Factory
     * @param players
     */
    private void generateBoatsFromFactory(List<PlayerInterface> players){
        //For each players add its boat to a list with all boats
        int i = 0;
        int j = 0;
        for (PlayerInterface p : players) {
            for (Map.Entry<Integer, BoatType> boatEntry : p.getFleet().entrySet()) {
                BoatInterface boat;

                Coord spawnPosition;
                Direction spawnDirection;
                switch (i){
                    case 0:
                        spawnPosition = new Coord(4, 2+j);
                        spawnDirection = Direction.EAST;
                        break;
                    case 1:
                        spawnPosition = new Coord(GameConfig.getGameGridWidth()-5-2+j, 4);
                        spawnDirection = Direction.SOUTH;
                        break;
                    case 2:
                        spawnPosition = new Coord(GameConfig.getGameGridWidth()-5, GameConfig.getGameGridHeight()-5-2+j);
                        spawnDirection = Direction.WEST;
                        break;
                    case 3:
                        spawnPosition = new Coord(2+j, GameConfig.getGameGridHeight()-5);
                        spawnDirection = Direction.NORTH;
                        break;
                    default:
                        System.out.println("BoatsImplementor ERROR: Generate fleet, there is too much fleets, please check your game configuration");
                        spawnPosition = new Coord(-1,-1);
                        spawnDirection = Direction.DEFAULT();
                        return;
                }
                boat = BoatFactory.newBoat(boatEntry.getKey(), boatEntry.getValue(), spawnPosition, p.getId());
                boat.setFacingDirection(spawnDirection);
                this.boats.add(boat);
                j++;
            }
            j = 0;
            i++;
        }
    }

    /**
     * // TODO Tests
     *
     * Shoot somewhere
     * @param target to select the destination coordinates
     * @return result of shot
     */
    public Pair<ResultShoot, ProcessedPosition> shootBoat(PlayerInterface currentPlayer, BoatInterface selectedBoat, Coord target) {
        // check if enough
        if (! currentPlayer.debitActionPoint(selectedBoat.getShootCost())){
            // if not we donnot shoot
            currentPlayer.undoLastAction();
            return new Pair<>(ResultShoot.FORBIDDEN, null);
        }

        BoatInterface boat = findBoatByCoord(target);
        try {
            Pair<ResultShoot, ProcessedPosition> resultShoot = boat.shoot(target);
            if (resultShoot.getFirst().equals(ResultShoot.DESTROYED)) {
                boat.destroy();
            }
            return(resultShoot);
        } catch (Exception e) { // TODO catch a custom exception like a "ShootException"
            return new Pair<>(ResultShoot.MISSED, null);
        }

    }

	@Override
	public List<Pair<ResultShoot, ProcessedPosition>> specialAction(PlayerInterface currentPlayer, BoatInterface selectedBoat, Coord target) {
        // check if enough AP
        if (! currentPlayer.debitActionPoint(selectedBoat.getSpecialActionCost())){
            // if not we donnot shoot
            currentPlayer.undoLastAction();
            return null;
        }

        /**
         *  TODO area shoot for special action is hard codded here.
         *  It's ok in a first time we should find a way to move this behavior in a proper class.
         *  But problem is how to access boats other things...
         */
        if(selectedBoat.getSpecialAction().getClass().equals(SpecialZoneAOE.class)){
            List<Pair<ResultShoot, ProcessedPosition>> result = new ArrayList<>();
            List<Coord> coords = ((SpecialZoneAOE)selectedBoat.getSpecialAction()).getEffectZone(target);
            for (Coord coord : coords) {
                result.add(this.shootBoat(currentPlayer, selectedBoat, coord));
                currentPlayer.creditActionPoint(1);
            }
            return result;
        }else{
            selectedBoat.actionSpecial(target);
        }
        return null;
    }

    /**
     * __TESTED__
     * TO BE CALLED FROM MODEL
     *
     * Move move a boat to the wanted destination if possible
     * Result may not change or may not be the desired position.
     *
     * @param selectedBoat is the boat to move
     * @param destination is the desired destination for the boat
     * @return ProcessedPositions (coords + direction)
     */
    public ProcessedPosition moveBoat(PlayerInterface currentPlayer, BoatInterface selectedBoat, Coord destination){
        int moveDistance = selectedBoat.getPivot().getDistanceTo(destination);
        if(
                ! currentPlayer.debitActionPoint(selectedBoat.getMoveCost(moveDistance))
                || ! selectedBoat.isMoveOk(destination)
                        //Check if the boat is allowed to move
                || !selectedBoat.canMove()
        ){
            // Return boat pos without moving
            currentPlayer.undoLastAction();
            return selectedBoat.getProcessedPosition();
        }

        //Prohibit the boat to move
        selectedBoat.hasMoved();
        //Return ProcessedPosition
        return this.moveBoatStepByStep(selectedBoat, destination).getFirst();
    }

    /**
     * __TESTED__
     * called by moveBoat()
     * @param boat is the boat to move
     * @param destination is the desired destination for the boat
     * @return ProcessedPositions (coords + direction)
     */
    private Pair<ProcessedPosition,ProcessedProps> moveBoatStepByStep(BoatInterface boat, Coord destination){
        // TODO sauvegarder valeur pivot bateau
        // TODO déplacer le bateau de 1 pas dans sa direction, et tant que ça marche, on continu jusqu'à destination
        // TODO appeler this.areCoordsAccessible(boat) à chaque fois. Si ça fail on stop
        // TODO set boat.lastPosition à la valeur sauvegardée (== on supprime l'historique de déplacement du step by step)

        // TODO => DONE
        Coord coord = new Coord(boat.getPivot().getX(), boat.getPivot().getY());
        Coord savedPivot = new Coord(boat.getPivot().getX(), boat.getPivot().getY());
        ProcessedProps processedProps = null;

        while(!coord.equals(destination)) {
            coord.addStepDirection(boat.getDirection(), 1);
            boat.move(coord);

            //Check if new coords of boat meet a mine
            processedProps = this.triggerMine(boat);

            //Check if new coords of boat meet a boat
            if (!this.areCoordsAccessible(boat)) {
                // move boat next to the ship without overlapping
                // TODO if you want to add an offset for the ship do not touch each other, it's here
                coord.addStepDirection(boat.getDirection(), -1);
                boat.moveHard(coord);
                // cancel historic of our multiple calls to move()
                //boat.setLastPosition(coord);
                return new Pair<ProcessedPosition, ProcessedProps>(boat.getProcessedPosition(), processedProps);
            }
        }
        //boat.setLastPosition(destination);
        return new Pair<ProcessedPosition, ProcessedProps>(boat.getProcessedPosition(), processedProps);
    }

    /**
     * __TESTED__
     * TO BE CALLED FROM MODEL
     *
     * Perform a quarter rotation for a boat
     *
     * @param selectedBoat is the boat to rotate
     * @param clockWise direction of rotation
     * @return ProcessedPositions (coords + direction)
     */
    public Pair<ProcessedPosition, ProcessedProps> rotateBoat(PlayerInterface currentPlayer, BoatInterface selectedBoat, boolean clockWise){
    	// check if enough
        if (! currentPlayer.debitActionPoint(selectedBoat.getRotateCost())){
            // if not we donnot rotate
            currentPlayer.undoLastAction();
            return new Pair<ProcessedPosition, ProcessedProps>(selectedBoat.getProcessedPosition(), null);
        }

        // rotate the boat
        if(clockWise){
            selectedBoat.rotateClockWise();
        }else{
            selectedBoat.rotateCounterClockWise();
        }

    	//Check if new coords of boat meet a mine
    	ProcessedProps processedProps = this.triggerMine(selectedBoat);

        // get its position
        ProcessedPosition processedPosition = selectedBoat.getProcessedPosition();


        // check if OK
        if(!this.areCoordsAccessible(selectedBoat)){
            // if boat collision, undo the move
            selectedBoat.undoLastMove();
            currentPlayer.undoLastAction();
            return new Pair<ProcessedPosition, ProcessedProps>(selectedBoat.getProcessedPosition(), processedProps);
        }

        // else, it's OK, return new pos
        return new Pair<ProcessedPosition, ProcessedProps>(processedPosition, processedProps);
    }

    /**
     * __PARTIALLY_TESTED__
     * TO BE CALLED FROM MODEL
     *
     * @param selectedBoat the boat to undo move
     * @return the new position data after undo (coords + direction)
     */
    public ProcessedPosition undoLastBoatMove(BoatInterface selectedBoat){
        selectedBoat.undoLastMove();

        return selectedBoat.getProcessedPosition();
    }

    /**
     * __PARTIALLY_TESTED__
     *
     * @param selectedBoat the boat to check for
     * @return if this position is allowed (else, undo it)
     */
    private boolean areCoordsAccessible(BoatInterface selectedBoat) {

        List<BoatInterface> boatsFound;
        for(Coord coord : selectedBoat.getCoords()){
            // check: out of bounds
            if(coord.getX() >= GameConfig.getGameGridWidth()
                || coord.getX() < 0
                || coord.getY() >= GameConfig.getGameGridHeight()
                || coord.getY() < 0)
            {
                return false;
            }
            // check: boat collision
            boatsFound = this.findBoatsByCoord(coord);
            for(BoatInterface boatFound : boatsFound){
                if(
                    boatFound != null               // if we found something here
                    && boatFound != selectedBoat    // and it's not the boat we are moving
                ){
                    // coord not accessible (there is collision)
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * __TESTED__
     * TODO Remove, deprecated, but algorithm may be useful ?
     *
     * Take a point (coord) and rotate it around another point (pivot)
     * by a given angle (in radius)
     *
     * Be careful, it works only with INTEGER coordinates.
     * So, do only rotation that are module of PI/2
     *
     * @param coord point to rotate
     * @param pivot point to rotate around
     * @param angleRadian rotation angle (in radian)
     * @return coord after rotation
     */
    @Deprecated
    protected Coord rotateAround(Coord coord, Coord pivot, float angleRadian){
        float sin = (float)Math.sin(angleRadian);
        float cos = (float)Math.cos(angleRadian);
        int x = coord.getX();
        int y = coord.getY();
        // translate point to origin
        x -= pivot.getX();
        y -= pivot.getY();
        // rotate point
        int newX = Math.round(x * cos - y * sin);
        int newY = Math.round(x * sin + y * cos);
        // translate back
        newX += pivot.getX();
        newY += pivot.getY();
        // add to result list
        return new Coord(newX, newY);
    }

    /**
     * __PARTIALLY_TESTED__
     *
     * This just find if there is a boat at the desired coordinates.
     * It accept any coordinate. Not only the pivot point.
     * @param coord is where to search for a boat
     * @return BoatInterface | null
     */
        public BoatInterface findBoatByCoord(Coord coord) {
        // TODO gérer la notion de joueur
    	for (BoatInterface boat : this.boats) {
            //Si on fait comme ça si on tire sur une épave c'est MISS
            if(boat.hasCoord(coord) && boat.getDestroy() == false){
                return boat;
            }
        }
        return null;
    }

    private List<BoatInterface> findBoatsByCoord(Coord coord) {
        // TODO gérer la notion de joueur
        List<BoatInterface> boatFound = new ArrayList<>();
        for (BoatInterface boat : this.boats) {
            if(boat.hasCoord(coord)){
                boatFound.add(boat);
            }
        }
        return boatFound;
    }

    public Map<Integer, ProcessedPosition> getBoats() {
        Map<Integer,ProcessedPosition> boatInitPos = new HashMap<>();
        for (BoatInterface boat: this.boats) {
            boatInitPos.put(boat.getId(),boat.getProcessedPosition());
        }
        return boatInitPos;
    }

    public Map<Integer, Integer> getBoatsAndPlayersId() {
        Map<Integer,Integer> boatsAndPlayersId = new HashMap<>();
        for (BoatInterface boat: this.boats) {
        	boatsAndPlayersId.put(boat.getId(),boat.getPlayerId());
        }
        return boatsAndPlayersId;
    }

    public List<Coord> getVisibleCoords(PlayerInterface player){
        List<BoatInterface> fleet = this.getPlayerFleet(player);
        List<Coord> visibleCoords = new ArrayList<>();
        for (BoatInterface boat : fleet) {
            List<Coord> coords = boat.getVisibleCoords();
            for (Coord visibleCoord : coords){
                if (!visibleCoords.contains(visibleCoord)){
                    visibleCoords.add(visibleCoord);
                }
            }
        }
        player.setVisibleCoords(visibleCoords);
        return visibleCoords;
    }

    @Override
    public int findPlayerIdFromBoat(BoatInterface boat) {
        return boat.getPlayerId();
    }

    public List<BoatInterface> getPlayerFleet(PlayerInterface player){
        List<BoatInterface> fleet = new ArrayList<>();
        for(int boatId : player.getFleet().keySet()){
            fleet.add(this.findBoayById(boatId));
        }
        return fleet;
    }

    public BoatInterface findBoayById(int boatId){
        for(BoatInterface boat : this.boats){
            if(boat.getId() == boatId){
                return boat;
            }
        }
        return null;
    }


	@Override
	public List<BoatInterface> getVisibleBoats(PlayerInterface player) {
        List<BoatInterface> fleet = this.getPlayerFleet(player);
        System.out.println(fleet);
		return fleet;
	}

	/**
	 *
	 * @param selectedBoat
	 * @return Coord of mine if we found mine. If not we return null
	 */
	public ProcessedProps triggerMine(BoatInterface selectedBoat){
		ProcessedProps processedProps = null;
		for(Coord coord : selectedBoat.getCoords()){
			if(this.mineImplementor.isMined(coord)){
				try {
					selectedBoat.setDammage(1);
					processedProps = this.mineImplementor.destroyMine(coord);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }
		return processedProps;
	}

	@Override
	public int getRemainsBoatsByPlayer(int playerId) {
		int nbBoat = 0;
		for (BoatInterface boatInterface : boats) {
			if (boatInterface.getPlayerId() == playerId && boatInterface.getDestroy() == false) {
				nbBoat++;
			}
		}
		return nbBoat;
	}

    @Override
    public void setProcessedPosition(ProcessedPosition processedPosition) {
        BoatInterface boat = this.findBoayById(processedPosition.boatId);
        boat.setProcessedPosition(processedPosition);
    }

    /**
     * The goal of this method is to put a mine
     * @param currentPlayer
     * @param selectedBoat
     * @return ProcessedProps
     */
    public ProcessedProps createMine(PlayerInterface currentPlayer, BoatInterface selectedBoat){
    	//Check if we have no mine and no boat at the destination
    	if (this.triggerMine(selectedBoat) == null && this.areCoordsAccessible(selectedBoat) && this.findBoatByCoord(selectedBoat.getCoordBehind()) ==null) {
    		return this.mineImplementor.createMine(selectedBoat.getCoordBehind(), currentPlayer.getId());
		}else{
			return null;
		}
    }

}