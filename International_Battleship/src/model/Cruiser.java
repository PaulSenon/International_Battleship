package model;


import tools.Coord;

public class Cruiser extends AbstractBoat {

    final int size = BoatName.Cruiser.getValue();
    int moveCost, shootCost, specialActionCost;

    public Cruiser(Coord coord, int id) {
        super(BoatName.Cruiser, coord, id);
        this.moveCost = BoatName.Cruiser.getValue();
        this.shootCost = BoatName.Cruiser.getValue()+1;
        this.specialActionCost = BoatName.Cruiser.getValue()+2;
    }

    @Override
    public int getMoveCost() {
        return this.moveCost;
    }

    @Override
    public int getShootCost() { return this.shootCost; }

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
