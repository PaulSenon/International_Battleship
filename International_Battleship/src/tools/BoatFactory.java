package tools;

import model.BoatInterface;
import model.BoatName;

public class BoatFactory {
    public static BoatInterface newBoat(BoatName boatName, Coord coord, int id) throws IllegalArgumentException {
        if(coord.getX()<0 || coord.getX()>GameConfig.getGameGridWidth() || coord.getY()<0 || coord.getX()>GameConfig.getGameGridHeight()) {
            throw new IllegalArgumentException("Les coordonnées du bateau doivent être comprise entre 0 et "+GameConfig.getGameGridHeight());
        }
        String className = "model." + boatName;
        return (BoatInterface) Introspection.newInstance (className, new Object[] {coord, id});
    }
}