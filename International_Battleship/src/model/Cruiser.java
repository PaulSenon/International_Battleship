package model;


import tools.Coord;

public class Cruiser extends AbstractBoat {

    public Cruiser(int id, Coord pivot, int playerId) {
        super(BoatType.Cruiser, id, pivot, playerId);
        mySpecialAction = new TargetAction() {
            @Override
            public void doAction(Coord target) {
                // TODO Auto-generated method stub
                System.out.println("Action spéciale croiseur");
            }
        };
    }

    @Override
    public void getVisibleArea() {

    }

    public int getSize( ) {
    	return this.size;
    }
}
