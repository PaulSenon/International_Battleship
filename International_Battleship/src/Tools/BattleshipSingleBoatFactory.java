package Tools;

import Model.Boat;

public class BattleshipSingleBoatFactory {
	public static Boat newBoat(String type, int x, int y){
		Boat boat = null;
		String className = "model.piece." + type;
		//boat = (Boat)
		return boat;
	}
}
