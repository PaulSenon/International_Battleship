package model;


import tools.Coord;

public class TorpedoBoat extends AbstractBoat {

    public TorpedoBoat(int id, Coord pivot, int playerId) {
        super(BoatType.TorpedoBoat, id, pivot, playerId);
    }

    @Override
    public void getVisibleArea() {

    }

    public int getSize() {
    	return this.size;
    }

}
