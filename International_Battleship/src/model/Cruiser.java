package model;


import tools.Coord;

public class Cruiser extends AbstractBoat {

    final int size = BoatName.Cruiser.getValue();
    final int moveCost = BoatName.Cruiser.getValue();
    final int shootCost = BoatName.Cruiser.getValue()+1;
    final int specialActionCost = BoatName.Cruiser.getValue()+2;

        public Cruiser(Coord coord, int id) {
    	super(BoatName.Cruiser, coord, id);
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

    public int getSize( ) {
    	return this.size;
    }
}
