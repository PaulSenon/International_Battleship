package tools;

import tools.Introspection;

import Model.Boat;
import Model.BoatName;
import Model.Coord;

public class BattleshipBoatFactory {
	public static Boat newBoat(BoatName boatName, Coord coord){
		String className = "Model." + boatName;
		Boat boat = (Boat) Introspection.newInstance (className, new Object[] {coord});
		return boat;
	}
}
