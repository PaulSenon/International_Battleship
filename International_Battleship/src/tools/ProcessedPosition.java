package tools;

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
    public List<CoordInfos> coords;

    public ProcessedPosition(Direction direction, List<CoordInfos> coord){
        this.direction = direction;
        this.coords = coord;
    }
}
