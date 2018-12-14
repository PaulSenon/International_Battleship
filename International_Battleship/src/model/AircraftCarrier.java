package model;


import tools.Coord;

public class AircraftCarrier extends AbstractBoat {

    public AircraftCarrier(int id, Coord pivot, int playerId) {
        super(BoatType.AircraftCarrier, id, pivot, playerId);
    }

    @Override
    public void getVisibleArea() {

    }

    public int getSize() {
    	return this.size;
    }
}
