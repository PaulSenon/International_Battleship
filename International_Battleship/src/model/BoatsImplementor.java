package model;


import tools.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoatsImplementor implements BoatsImplementorInterface {

        private List<BoatInterface> boats;


    public BoatsImplementor(List<PlayerInterface> players) {
        this.boats = new ArrayList<>();
        this.generateBoatsFromFactory(players);
    }

    /**
     * This method generates boat from Factory
     * @param players
     */
    private void generateBoatsFromFactory(List<PlayerInterface> players){
        //For each players add its boat to a list with all boats
        int i = 2;
        int j = 2;
        for (PlayerInterface p : players) {
            for (Map.Entry<Integer, BoatType> boatEntry : p.getFleet().entrySet()) {
                BoatInterface boat;
                if(i == 2){
                    boat = BoatFactory.newBoat(boatEntry.getKey(), boatEntry.getValue(), new Coord(5,j), p.getId());
                }else{
                    boat = BoatFactory.newBoat(boatEntry.getKey(), boatEntry.getValue(), new Coord(20,j+10), p.getId());
                    boat.setFacingDirection(Direction.WEST);
                }
                this.boats.add(boat);
                j++;
            }
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
        if (! currentPlayer.debitActionPoint(selectedBoat.getRotateCost())){
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
         *  It's ok in a first time we we should find a way to move this behavior in a proper class.
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
    public ProcessedPosition moveBoat(PlayerInterface currentPlayer, BoatInterface selectedBoat, Coord destination) {
        int moveDistance = selectedBoat.getPivot().getDistanceTo(destination);
        if(
                ! currentPlayer.debitActionPoint(selectedBoat.getMoveCost(moveDistance))
                || ! selectedBoat.isMoveOk(destination)
        ){
            // Return boat pos without moving
            currentPlayer.undoLastAction();
            return selectedBoat.getProcessedPosition();
        }

        return this.moveBoatStepByStep(selectedBoat, destination);
    }

    /**
     * __TESTED__
     * called by moveBoat()
     * @param boat is the boat to move
     * @param destination is the desired destination for the boat
     * @return ProcessedPositions (coords + direction)
     */
    private ProcessedPosition moveBoatStepByStep(BoatInterface boat, Coord destination){
        // TODO sauvegarder valeur pivot bateau
        // TODO déplacer le bateau de 1 pas dans sa direction, et tant que ça marche, on continu jusqu'à destination
        // TODO appeler this.areCoordsAccessible(boat) à chaque fois. Si ça fail on stop
        // TODO set boat.lastPosition à la valeur sauvegardée (== on supprime l'historique de déplacement du step by step)

        // TODO => DONE
        Coord coord = new Coord(boat.getPivot().getX(), boat.getPivot().getY());
        Coord savedPivot = new Coord(boat.getPivot().getX(), boat.getPivot().getY());

        while(!coord.equals(destination)) {
            coord.addStepDirection(boat.getDirection(), 1);
            boat.move(coord);
            if (!this.areCoordsAccessible(boat)) {
                // move boat next to the ship without overlapping
                // TODO if you want to add an offset for the ship do not touch each other, it's here
                coord.addStepDirection(boat.getDirection(), -1);
                boat.moveHard(coord);

                // cancel historic of our multiple calls to move()
//                boat.setLastPosition(coord);

                return boat.getProcessedPosition();
            }
        }

//        boat.setLastPosition(destination);
        return boat.getProcessedPosition();
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
    public ProcessedPosition rotateBoat(PlayerInterface currentPlayer, BoatInterface selectedBoat, boolean clockWise){
        // check if enough
        if (! currentPlayer.debitActionPoint(selectedBoat.getRotateCost())){
            // if not we donnot rotate
            currentPlayer.undoLastAction();
            return selectedBoat.getProcessedPosition();
        }

        // rotate the boat
        if(clockWise){
            selectedBoat.rotateClockWise();
        }else{
            selectedBoat.rotateCounterClockWise();
        }

        // get its position
        ProcessedPosition processedPosition = selectedBoat.getProcessedPosition();

        // check if OK
        if(!this.areCoordsAccessible(selectedBoat)){
            // if boat collision, undo the move
            selectedBoat.undoLastMove();
            return selectedBoat.getProcessedPosition();
        }

        // else, it's OK, return new pos
        return processedPosition;
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

    private List<BoatInterface> getPlayerFleet(PlayerInterface player){
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

	public void setProcessedPosition(ProcessedPosition processedPosition){
        BoatInterface boat = this.findBoayById(processedPosition.boatId);
        boat.setProcessedPosition(processedPosition);
    }

}