package model;


import tools.Coord;

public class Submarine extends AbstractBoat {

    public Submarine(int id, Coord pivot, int playerId) {
        super(BoatType.Submarine, id, pivot, playerId);
        this.mySpecialAction = new InstantAction(){
            @Override
            public void doAction(Coord target) {
                // TODO Auto-generated method stub
                System.out.println("Action spéciale sous marin");
            }
        };
    }

    @Override
    public void getVisibleArea() {

    }

    public int getSize () {
    	return this.size;
    }

}
