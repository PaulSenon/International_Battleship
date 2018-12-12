package model;


import tools.Coord;

public class Sentinel extends AbstractBoat {

    final int size = BoatName.Sentinel.getValue();
    int moveCost, shootCost, specialActionCost;

    public Sentinel(Coord coord, int id) {
        super(BoatName.Sentinel, coord, id);
        this.moveCost = BoatName.Sentinel.getValue();
        this.shootCost = BoatName.Sentinel.getValue()+1;
        this.specialActionCost = BoatName.Sentinel.getValue()+2;
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
