package testTools;

import model.AbstractBoat;
import model.BoatName;
import model.ResultSpecialAction;
import tools.Coord;
import tools.Direction;

public class TestObjectsGenerator {

    public AbstractBoat generateTestBoat(Coord pivot, int size, Direction direction){
        AbstractBoat boat = new AbstractBoat(BoatName.Cruiser, pivot){

			@Override
			public ResultSpecialAction actionSpecial(Coord target) {
				return null;
				
			}};
        Reflection.setFieldByReflection2(AbstractBoat.class, boat, "facingDirection", direction);
        Reflection.setFieldByReflection2(AbstractBoat.class, boat, "size", size);
        return boat;
    }

}
