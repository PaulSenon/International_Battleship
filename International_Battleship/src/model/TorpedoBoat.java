package model;


import tools.Coord;

public class TorpedoBoat extends AbstractBoat {

	final int size = BoatName.TorpedoBoat.getValue();
    final int moveCost = BoatName.TorpedoBoat.getValue();
    final int shootCost = BoatName.TorpedoBoat.getValue()+1;
    final int specialActionCost = BoatName.TorpedoBoat.getValue()+2;

        public TorpedoBoat(Coord coord, int id) {
    	super(BoatName.TorpedoBoat, coord, id);
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
