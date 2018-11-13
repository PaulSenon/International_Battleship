package Model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

import tools.BattleshipBoatFactory;
import tools.Coord;
import tools.Direction;

@objid ("dcf26cb5-3322-4d9d-98af-5b54a0f09632")
public class BoatsImplementor implements BattleshipGameImplementor {

    @objid ("6eaf860c-9a4a-4dab-888f-d3e4f31d9e77")
    private List<Boat> boats;


    @objid ("5ec8880b-f75d-4bee-8ed0-ecea6a4d4930")
    public BoatsImplementor(List<Player> players, List<BoatName> fleetList) {
        this.boats = new ArrayList<>();
        this.generateBoatsFromFactory(players, fleetList);
    }

    private void generateBoatsFromFactory(List<Player> players, List<BoatName> fleetList){
        for (Player p : players) {
            int i=0;
            for (BoatName boatName  : fleetList) {
                p.getFleet().add(BattleshipBoatFactory.newBoat(boatName,new Coord(0,i)));
                i++;
            }
            //test
            System.out.println("L'implementor a générer un bateau de type "+ p.getFleet());
        }

    }

    /**
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
    public Coord move(Boat selectedBoat, Coord destination) {
        // TODO selectedBoat.isMoveOk(coord) ?
            // => le bateau va regarder si les coords sont bien devant lui
        // TODO faire le déplacement du bateau si possible, (et le plus loin possible)
        // => retourne les nouvelles coordonnées du pivot du bateau

        return new Coord(0,0 );//TODO
    }

    /**
     * TO BE CALLED FROM MODEL
     *
     * Perform a quarter clock wise rotation for a boat
     *
     * @param selectedBoat is the boat to rotate
     * @return Direction is the boat facing direction after processing (may not change)
     */
    public Direction rotateBoatClockWise(Boat selectedBoat) {
        List<Coord> coords = this.rotate(selectedBoat,1);
        // TODO vérifier que la liste de coords soit accessible (pas bateau)
        // TODO si ok selectedBoat.rotateCW()
        // TODO return selectedBoat.getDirection()

        // TODO just a debug placeholder :
        return Direction.DEFAULT;
    }

    /**
     * TO BE CALLED FROM MODEL
     *
     * Perform a quarter counter clock wise rotation for a boat
     *
     * @param selectedBoat is the boat to rotate
     * @return Direction is the boat facing direction after processing (may not change)
     */
    public Direction rotateBoatCounterClockWise(Boat selectedBoat) {

        // get the list of POTENTIALS coordinate where the boat should be after
        // performing the desired rotation. But it do not rotate it !
        List<Coord> coords = this.rotate(selectedBoat,-1);

        // TODO vérifier que la liste de coords soit accessible (pas de bateau)
        // TODO si ok selectedBoat.rotateCW()
        // TODO return selectedBoat.getDirection()

        // TODO just a debug placeholder
        return Direction.DEFAULT;
    }

    /**
     * Get a boat new coordinate after a quarter rotation
     * >=1 = clock wise
     * <1  = counter clock wise
     * @param direction use 0 or 1 for example
     * @return successful
     */
    private List<Coord> rotate(Boat boat, int direction){
        float angleRadius = (float) ((direction > 0) ? Math.PI/2 : -Math.PI/2);

        Coord pivot = boat.getPivot();
        List<Coord> coords = boat.getCoords();
        List<Coord> newCoords = new ArrayList<>();
        for (Coord coord : coords) {
            newCoords.add(this.rotateAround(coord, pivot, angleRadius));
        }

        return newCoords;
    }

    /**
     * TODO move in Coord class or a new tool class for geometric computation
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
    private Coord rotateAround(Coord coord, Coord pivot, float angleRadian){
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
     * This just find if there is a boat at the desired coordinates.
     * It accept any coordinate. Not only the pivot point.
     * @param coord is where to search for a boat
     * @return Boat | null
     */
    @objid ("4643b543-6571-4c67-bf46-c267384eea71")
    public Boat findBoatByCoord(Coord coord) {
        // TODO gérer la notion de joueur
        for (Boat boat : this.boats) {
            if(boat.hasCoord(coord)){
                return boat;
            }
        }
        return null;
    }

}