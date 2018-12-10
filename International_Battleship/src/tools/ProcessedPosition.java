package tools;

import model.BoatName;

import java.util.List;


/**
 * This class is used to transfer a whole boat position information
 * to the view to allow drawing it.
 *
 * TODO this class may change, feel free to add some other properties if necessary
 */
public class ProcessedPosition {
    public final List<Coord> visibleCoords;
    public Direction direction;
    // TODO we do not transfer Coord from model to view. So do we need to change this here ?
    public List<Coord> coords;
    public int boatId;
    public BoatName name;
    public List<Integer> brokenPartIds;

    public ProcessedPosition(int boatId, BoatName name, Direction direction, List<Coord> coord, List<Integer> brokenPartIds, List<Coord> visibleCoords){
        this.boatId = boatId;
        this.direction = direction;
        this.coords = coord;
        this.name = name;
        this.brokenPartIds = brokenPartIds;
        this.visibleCoords = visibleCoords;
    }
}
