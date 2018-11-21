package model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.BoatFactory;
import tools.Coord;
import tools.ProcessedPosition;
import tools.ResultShoot;

import java.util.ArrayList;
import java.util.List;

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
            int i=0;
            for (BoatName boatName  : fleetList) {
                p.getFleet().add(BoatFactory.newBoat(boatName,new Coord(0,i)));
                i++;
            }
            //test
            System.out.println("L'implementor a générer un bateau de type "+ p.getFleet());
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
     * // TODO Tests
     * TO BE CALLED FROM MODEL
     *
     * Move move a boat to the wanted destination if possible
     * Result may not change or may not be the desired position.
     *
     * @param selectedBoat is the boat to move
     * @param destination is the desired destination for the boat
     * @return Coord is the coordinates of boat pivot after processing
     */
    @objid ("262ccb08-0aa5-49fd-9237-4805c3304fb9")
    public Coord move(BoatInterface selectedBoat, Coord destination) {
        // TODO selectedBoat.isMoveOk(coord) ?
            // => le bateau va regarder si les coords sont bien devant lui
        // TODO faire le déplacement du bateau si possible, (et le plus loin possible)
        // => retourne les nouvelles coordonnées du pivot du bateau
    	if(destination.getX()>0 && destination.getY()>0){
    		selectedBoat.setPivot(destination);
    	}
        return destination;//TODO
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
        BoatInterface foundBoat;
        for(Coord coord : selectedBoat.getCoords()){
            foundBoat = this.findBoatByCoord(coord);
            if(
                foundBoat != null               // if we found something here
                && foundBoat != selectedBoat    // and it's not the boat we are moving
            ){
                // coord not accessible (there is collision)
                return false;
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

}