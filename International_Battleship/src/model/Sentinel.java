package model;


import tools.Coord;

public class Sentinel extends AbstractBoat {

    public Sentinel(int id, Coord pivot, int playerId) {
        super(BoatType.Sentinel, id, pivot, playerId);
    }

    @Override
    public void getVisibleArea() {

    }

    public int getSize () {
    	return this.size;
    }

}
