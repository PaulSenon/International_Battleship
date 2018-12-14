package model;


import tools.Coord;

public class Submarine extends AbstractBoat {

    public Submarine(int id, Coord pivot, int playerId) {
        super(BoatType.Submarine, id, pivot, playerId);
    }

    @Override
    public void getVisibleArea() {

    }

    public int getSize () {
    	return this.size;
    }

}
