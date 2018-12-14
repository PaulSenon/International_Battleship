package model;


import tools.Coord;

public class Cruiser extends AbstractBoat {

    public Cruiser(int id, Coord pivot, int playerId) {
        super(BoatType.Cruiser, id, pivot, playerId);
    }

    @Override
    public void getVisibleArea() {

    }

    public int getSize( ) {
    	return this.size;
    }
}
