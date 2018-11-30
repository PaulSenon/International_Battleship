package model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.BoatFactory;
import tools.Coord;
import tools.ProcessedPosition;
import tools.ResultShoot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@objid ("dcf26cb5-3322-4d9d-98af-5b54a0f09632")
public class BoatsImplementor implements BoatsImplementorInterface {

    @objid ("6eaf860c-9a4a-4dab-888f-d3e4f31d9e77")
    private List<BoatInterface> boats;


    @objid ("5ec8880b-f75d-4bee-8ed0-ecea6a4d4930")
    public BoatsImplementor(List<Player> players, List<BoatName> fleetList) {
        this.boats = new ArrayList<>();
        this.generateBoatsFromFactory(players, fleetList);
    }

    private void generateBoatsFromFactory(List<Player> players, List<BoatName> fleetList){
        for (Player p : players) {
            int i=5;
            for (BoatName boatName  : fleetList) {
                try {
                    p.getFleet().add(BoatFactory.newBoat(boatName,new Coord(5,i)));
                    i++;
                }catch (IllegalArgumentException e){
                    e.getMessage();
                }
            }
            //test
            System.out.println("L'implementor a générer la flotte suivante : "+ p.getFleet());
            for(BoatInterface boat : p.getFleet()){
            	this.boats.add(boat);
            }
        }

    }

    /**
     * // TODO Tests
     *
     * Shoot from a boat to somewhere
     * @param boatCoord to select the boat to shoot with
     * @param target to select the destination coordinates
     * @return hit
     */
	@objid ("f561c936-de20-43ae-a170-a9290c7f975c")
    public boolean shoot(Coord boatCoord, Coord target) {
        return false;
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
    @objid ("262ccb08-0aa5-49fd-9237-4805c3304fb9")
    public ProcessedPosition moveBoat(BoatInterface selectedBoat, Coord destination) {
        if(selectedBoat.isMoveOk(destination)){
            return this.moveBoatStepByStep(selectedBoat, destination);
        }

        return selectedBoat.getProcessedPosition();
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
    public ProcessedPosition rotateBoat(BoatInterface selectedBoat, boolean clockWise){
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

    @Override
    public ResultShoot shoot(Coord target) {
        return ResultShoot.MISSED;
//        return boat.shoot(target);
    }

    /**
     * __PARTIALLY_TESTED__
     *
     * This just find if there is a boat at the desired coordinates.
     * It accept any coordinate. Not only the pivot point.
     * @param coord is where to search for a boat
     * @return BoatInterface | null
     */
    @objid ("4643b543-6571-4c67-bf46-c267384eea71")
    public BoatInterface findBoatByCoord(Coord coord) {
        // TODO gérer la notion de joueur
    	for (BoatInterface boat : this.boats) {
    		if(boat.hasCoord(coord)){
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

    public Map<BoatName, ProcessedPosition> getBoats() {
        Map<BoatName,ProcessedPosition> boatInitPos = new HashMap<>();
        for (BoatInterface boat: this.boats) {
            boatInitPos.put(boat.getName(),boat.getProcessedPosition());
        }
        return boatInitPos;
    }

	@Override
	public void specialAction(BoatInterface selectedBoat,Coord coordSquare) {
		System.out.println(selectedBoat);
		for(BoatInterface boat : this.boats){
			if(boat.equals(selectedBoat)){
				boat.actionSpecial(coordSquare);
			}
		}
		
	}

}