package model;


import tools.Coord;

public class TorpedoBoat extends AbstractBoat {

    final int size = BoatName.TorpedoBoat.getValue();
    int moveCost, shootCost, specialActionCost;

    public TorpedoBoat(Coord coord, int id) {
        super(BoatName.TorpedoBoat, coord, id);
        this.moveCost = BoatName.TorpedoBoat.getValue();
        this.shootCost = BoatName.TorpedoBoat.getValue()+1;
        this.specialActionCost = BoatName.TorpedoBoat.getValue()+2;
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
