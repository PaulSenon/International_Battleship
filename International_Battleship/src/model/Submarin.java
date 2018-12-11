package model;


import tools.Coord;

public class Submarin extends AbstractBoat {

    final int size = BoatName.Submarin.getValue();
    final int moveCost = BoatName.Submarin.getValue();
    final int shootCost = BoatName.Submarin.getValue()+1;
    final int specialActionCost = BoatName.Submarin.getValue()+2;

        public Submarin(Coord coord, int id) {
    	super(BoatName.Submarin, coord, id);
    }

        public void shoot() {
    }

        public void move() {
    }

        public void hourlyRotation() {
    }

        public void antiHourlyRotation() {
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
