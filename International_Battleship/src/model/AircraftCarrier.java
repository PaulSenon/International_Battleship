package model;


import tools.Coord;

public class AircraftCarrier extends AbstractBoat {

    public AircraftCarrier(int id, Coord pivot, int playerId) {
        super(BoatType.AircraftCarrier, id, pivot, playerId);
    	mySpecialAction = new TargetAction(){
			@Override
			public void doAction(Coord target) {
				// TODO Auto-generated method stub
				System.out.println("Action spécial porte avion");
			}
    	};
    }

    @Override
    public void getVisibleArea() {

    }

    public int getSize() {
    	return this.size;
    }
}
