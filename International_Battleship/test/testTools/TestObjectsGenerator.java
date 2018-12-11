package testTools;

import model.AbstractBoat;
import model.BoatName;
import tools.Coord;
import tools.Direction;

public class TestObjectsGenerator {

    public AbstractBoat generateTestBoat(Coord pivot, int size, Direction direction){
        AbstractBoat boat = new AbstractBoat(BoatName.Cruiser, pivot, 0) {
            @Override
            public int getMoveCost() {
                return 0;
            }

            @Override
            public int getShootCost() {
                return 0;
            }

            @Override
            public int getSpecialActionCost() {
                return 0;
            }

            @Override
            public void getVisibleArea() {

            }
        };
        Reflection.setFieldByReflection2(AbstractBoat.class, boat, "facingDirection", direction);
        Reflection.setFieldByReflection2(AbstractBoat.class, boat, "size", size);
        return boat;
    }

}
