package tools;

import model.BoatType;

import java.util.List;


/**
 * This class is used to transfer a whole boat position information
 * to the view to allow drawing it.
 *
 * TODO this class may change, feel free to add some other properties if necessary
 */
public class ProcessedPosition {
    public Direction direction;
    // TODO we do not transfer Coord from model to view. So do we need to change this here ?
    public List<Coord> coords;
    public int boatId;
    public BoatType name;
    public List<Integer> brokenPartIds;
    public Coord pivot;

    public ProcessedPosition(
            int boatId,
            BoatType name,
            Coord pivot,
            Direction direction,
            List<Coord> coord,
            List<Integer> brokenPartIds
    ){
        this.boatId = boatId;
        this.name = name;
        this.pivot = pivot;
        this.direction = direction;
        this.coords = coord;
        this.brokenPartIds = brokenPartIds;
    }
}
