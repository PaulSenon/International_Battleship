package tools;

import Model.Boat;
import Model.BoatName;

public class BattleshipBoatFactory {
    public static Boat newBoat(BoatName boatName, Coord coord){
        String className = "Model." + boatName;
        return (Boat) Introspection.newInstance (className, new Object[] {coord});
    }
}