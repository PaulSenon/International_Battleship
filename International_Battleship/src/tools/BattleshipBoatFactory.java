package tools;

import tools.Introspection;

import Model.Boat;
import Model.Coord;
import Model.Sentinel; 

public class BattleshipBoatFactory {
	public static Boat newBoat(String type){
		String className = "Model." + type;
		Coord coordBoat = new Coord(2,1);
		Boat boat = new Sentinel(coordBoat);
		boat = (Boat) Introspection.newInstance (className, new Object[] {coordBoat});
		return boat;
	}
}
