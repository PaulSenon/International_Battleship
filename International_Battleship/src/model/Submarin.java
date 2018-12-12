package model;


import tools.Coord;

public class Submarin extends AbstractBoat {

    final int size = BoatName.Submarin.getValue();
    int moveCost, shootCost, specialActionCost;

    public Submarin(Coord coord, int id) {
        super(BoatName.Submarin, coord, id);
        this.moveCost = BoatName.Submarin.getValue();
        this.shootCost = BoatName.Submarin.getValue()+1;
        this.specialActionCost = BoatName.Submarin.getValue()+2;
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
