package model;


import tools.Coord;

public class Sentinel extends AbstractBoat {

    final int size = BoatName.Sentinel.getValue();
    final int moveCost = BoatName.Sentinel.getValue();
    final int shootCost = BoatName.Sentinel.getValue()+1;
    final int specialActionCost = BoatName.Sentinel.getValue()+2;

	    public Sentinel(Coord coord, int id) {
		super(BoatName.Sentinel, coord, id);
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

    public int getSize () {
    	return this.size;
    }

}
