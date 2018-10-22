package Tools;

import Tools.Introspection;

import Model.Boat;
import Model.Coord; 

public class BattleshipSingleBoatFactory {
	public static Boat newBoat(String type){
		Boat boat = null;
		String className = "Model." + type;
		Coord coordBoat = new Coord(10,10);
		boat = (Boat) Introspection.newInstance (className, new Object[] {coordBoat});
		return boat;
	}
}
