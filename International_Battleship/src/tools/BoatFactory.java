package tools;

import model.BoatInterface;
import model.BoatType;

public class BoatFactory {
    public static BoatInterface newBoat(int id, BoatType boatType, Coord coord, int playerId) throws IllegalArgumentException {
        if(coord.getX()<0 || coord.getX()>GameConfig.getGameGridWidth() || coord.getY()<0 || coord.getX()>GameConfig.getGameGridHeight()) {
            throw new IllegalArgumentException("Les coordonnées du bateau doivent être comprise entre 0 et "+GameConfig.getGameGridHeight());
        }
        String className = "model." + boatType;
        return (BoatInterface) Introspection.newInstance (className, new Object[] {id, coord, playerId});
    }
}