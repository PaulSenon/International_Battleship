package model;


import tools.Coord;

public class AircraftCarrier extends AbstractBoat {

    final int size = BoatName.AircraftCarrier.getValue();
    int moveCost, shootCost, specialActionCost;

    public AircraftCarrier(Coord coord, int id) {
        super(BoatName.AircraftCarrier, coord, id);
        this.moveCost = BoatName.AircraftCarrier.getValue();
        this.shootCost = BoatName.AircraftCarrier.getValue()+1;
        this.specialActionCost = BoatName.AircraftCarrier.getValue()+2;
    }

    @Override
    public int getMoveCost() {
        return this.moveCost;
    }

    @Override
    public int getShootCost() {
        return this.shootCost;
    }

    @Override
    public int getSpecialActionCost() {
        return this.specialActionCost;
    }

    @Override
    public void getVisibleArea() {

    }

    public int getSize() {
    	return this.size;
    }
}
