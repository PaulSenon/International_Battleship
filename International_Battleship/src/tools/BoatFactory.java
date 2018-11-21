package tools;

import model.BoatInterface;
import model.BoatName;

public class BoatFactory {
    public static BoatInterface newBoat(BoatName boatName, Coord coord){
        String className = "model." + boatName;
        return (BoatInterface) Introspection.newInstance (className, new Object[] {coord});
    }
}