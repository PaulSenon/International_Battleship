package tools;

import model.BoatType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is used to transfer a whole boat position information
 * to the view to allow drawing it.
 *
 * TODO this class may change, feel free to add some other properties if necessary
 */
public class ProcessedPosition implements Serializable{


    static final long serialVersionUID = 6734671468716873L;

    public Direction direction;
    // TODO we do not transfer Coord from model to view. So do we need to change this here ?
    public List<Coord> coords;
    public int boatId;
    public BoatType name;
    private List<SerInteger> brokenPartIds;
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
        List<SerInteger> vals = new ArrayList<>();//C'est dégueu mais ça marche
        for (int i:brokenPartIds) {
            vals.add(new SerInteger(i));
        }
        this.brokenPartIds = vals;
    }

    @Override
    public String toString() {
        return "ProcessedPosition{" +
                "direction=" + direction +
                ", coords=" + coords +
                ", boatId=" + boatId +
                ", name=" + name +
                ", brokenPartIds=" + brokenPartIds +
                ", pivot=" + pivot +
                '}';
    }


    public List<Integer>  getBrokenPartIds(){
        List<Integer> val = new ArrayList<>();
        for (SerInteger i: brokenPartIds) {
            val.add(new Integer (i.getVal()));
        }
    return val;
    }
}