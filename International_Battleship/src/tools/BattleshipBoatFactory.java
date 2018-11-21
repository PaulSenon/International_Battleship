package tools;

import model.Boat;
import model.BoatName;

public class BattleshipBoatFactory {
    public static Boat newBoat(BoatName boatName, Coord coord){
        String className = "model." + boatName;
        return (Boat) Introspection.newInstance (className, new Object[] {coord});
    }
}