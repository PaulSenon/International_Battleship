package testTools;

import model.AbstractBoat;
import model.BoatType;
import tools.Coord;
import tools.Direction;

public class TestObjectsGenerator {

    public AbstractBoat generateTestBoat(Coord pivot, int size, Direction direction){
        AbstractBoat boat = new AbstractBoat(BoatType.Cruiser, 0, pivot, 0){

			@Override
			public void actionSpecial(Coord coordSquare) {
				// TODO Auto-generated method stub

			}

            @Override
            public void getVisibleArea() {

            }
            @Override
            protected int getMoveCostByUnit(){
                return 0;
            }
            @Override
            public int getRotateCost(){
                return 0;
            }
            @Override
            public int getShootCost(){
                return 0;
            }
            @Override
            public int getSpecialActionCost(){
                return 0;
            }
        };
        Reflection.setFieldByReflection2(AbstractBoat.class, boat, "facingDirection", direction);
        Reflection.setFieldByReflection2(AbstractBoat.class, boat, "size", size);
        return boat;
    }

}
