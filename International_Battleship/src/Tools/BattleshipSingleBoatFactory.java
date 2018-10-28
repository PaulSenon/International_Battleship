package Tools;

import Tools.Introspection;

import Model.Boat;
import Model.Coord;
import Model.Sentinel; 

public class BattleshipSingleBoatFactory {
	public static Boat newBoat(String type){
		String className = "Model." + type;
		Coord coordBoat = new Coord(10,10);
		Boat boat = new Sentinel(coordBoat);
		boat = (Boat) Introspection.newInstance (className, new Object[] {coordBoat});
		return boat;
	}
}
