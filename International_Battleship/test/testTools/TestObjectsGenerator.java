package testTools;

import Model.AbstractBoat;
import Model.BoatName;
import tools.Coord;
import tools.Direction;

public class TestObjectsGenerator {

    public AbstractBoat generateTestBoat(Coord pivot, int size, Direction direction){
        AbstractBoat boat = new AbstractBoat(BoatName.Cruiser, pivot) {
            @Override
            public void move() { }
            @Override
            public void hourlyRotation() { }
            @Override
            public void antiHourlyRotation() { }
        };
        Reflection.setFieldByReflection2(AbstractBoat.class, boat, "facingDirection", direction);
        Reflection.setFieldByReflection2(AbstractBoat.class, boat, "size", size);
        return boat;
    }

}
